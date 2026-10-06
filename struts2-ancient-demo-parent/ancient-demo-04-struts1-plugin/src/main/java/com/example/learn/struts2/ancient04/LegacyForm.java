package com.example.learn.struts2.ancient04;

import org.apache.struts.action.ActionForm;

public class LegacyForm extends ActionForm {
    private String note;
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}