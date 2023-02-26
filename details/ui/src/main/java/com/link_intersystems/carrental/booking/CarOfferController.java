package com.link_intersystems.carrental.booking;

import com.link_intersystems.carrental.MessageDialog;
import com.link_intersystems.carrental.offer.*;
import com.link_intersystems.swing.action.AbstractWorkerAction;
import com.link_intersystems.swing.action.BackgroundProgress;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

public class CarOfferController extends AbstractWorkerAction<CarOfferResponseModel, Void> {

    private CarOfferUseCase carOfferUseCase;

    private DefaultListModel<CarOfferModel> carOfferListModel = new DefaultListModel<>();

    private CarSearchModel carSearchModel;
    private CarOfferPresenter carOfferPresenter = new CarOfferPresenter();
    private MessageDialog messageDialog;

    public CarOfferController(CarOfferUseCase carOfferUseCase, CarSearchModel carSearchModel) {
        this.carOfferUseCase = carOfferUseCase;
        this.carSearchModel = carSearchModel;

        putValue(Action.NAME, "Search");
    }

    public ListModel<CarOfferModel> getCarOfferListModel() {
        return carOfferListModel;
    }

    public CarSearchModel getCarSearchModel() {
        return carSearchModel;
    }

    public void setMessageDialog(MessageDialog messageDialog) {
        this.messageDialog = messageDialog;
    }

    @Override
    protected CarOfferResponseModel doInBackground(BackgroundProgress<Void> backgroundProgress) throws Exception {
        CarOfferRequestModel requestModel = carOfferPresenter.toRequestModel(carSearchModel);
        return carOfferUseCase.makeOffers(requestModel);
    }

    @Override
    protected void done(CarOfferResponseModel responseModel) {
        CarOffersOutputModel carOffers = responseModel.getCarOffers();

        carOfferListModel.clear();
        for (CarOfferOutputModel carOffer : carOffers) {
            CarOfferModel carOfferModel = carOfferPresenter.toCarOfferModel(carOffer);
            carOfferListModel.addElement(carOfferModel);
        }
    }

    @Override
    protected void failed(ExecutionException e) {
        if (messageDialog != null) {
            messageDialog.showException(e.getCause());
        }
    }
}