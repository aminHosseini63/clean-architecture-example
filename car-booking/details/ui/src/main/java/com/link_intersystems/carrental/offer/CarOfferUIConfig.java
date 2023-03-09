package com.link_intersystems.carrental.offer;

import com.link_intersystems.carrental.booking.CarBookingController;
import com.link_intersystems.carrental.booking.CarBookingUseCase;
import com.link_intersystems.carrental.swing.notification.MessageDialog;

import java.time.LocalDateTime;

public class CarOfferUIConfig {

    private MessageDialog messageDialog;

    public CarOfferUIConfig(MessageDialog messageDialog) {

        this.messageDialog = messageDialog;
    }

    public CarOfferView getCarOfferView(CarOfferController carOfferController, CarBookingController carBookingController) {
        CarOfferView carOfferView = new CarOfferView();
        carOfferView.setCarOfferListModel(carOfferController.getCarOfferListModel());
        carOfferView.setCarSearchModel(carOfferController.getCarSearchModel());
        carOfferView.setCarSearchAction(carOfferController);
        carOfferView.setBookCarAction(carBookingController);
        carOfferView.getSelectionProvider().addSelectionChangedListener(carBookingController.getSelectionListener());
        return carOfferView;
    }

    public CarBookingController getCarBookingController(CarBookingUseCase carBookingUseCase, CarOfferController carOfferController) {
        CarBookingController carBookingController = new CarBookingController(carBookingUseCase, messageDialog);
        carBookingController.setOnDoneActionListener(carOfferController);
        return carBookingController;
    }

    public CarOfferController getCarOfferController(CarOfferUseCase carOfferUseCase, MessageDialog messageDialog) {
        CarOfferController carOfferController = new CarOfferController(carOfferUseCase);
        CarSearchModel carSearchModel = carOfferController.getCarSearchModel();
        initCarSearchModel(carSearchModel);
        carOfferController.setMessageDialog(messageDialog);
        return carOfferController;
    }


    private void initCarSearchModel(CarSearchModel carSearchModel) {
        carSearchModel.getVehicleType().setValue("MICRO");
        carSearchModel.getPickupDate().setValue(LocalDateTime.now().plusDays(1).toString());
        carSearchModel.getReturnDate().setValue(LocalDateTime.now().plusDays(2).toString());
    }

}