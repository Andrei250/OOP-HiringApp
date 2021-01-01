package com.pachetepachete.Views;

import com.pachetepachete.Models.Consumer;
import com.pachetepachete.Models.Job;
import com.pachetepachete.Models.Request;

import javax.swing.*;
import java.awt.*;

public class RequestsRenderer implements ListCellRenderer<Request<Job, Consumer>> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Request<Job, Consumer>> list, Request<Job, Consumer> value, int index, boolean isSelected, boolean cellHasFocus) {
        return null;
    }
}
