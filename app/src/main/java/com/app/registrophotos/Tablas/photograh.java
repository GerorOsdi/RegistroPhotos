package com.app.registrophotos.Tablas;

public class photograh {
    private Integer ID;
    private String IMAGE;
    private String DESC;

    public photograh(){
        //Contructor vacío
    }

    public photograh(Integer ID, String IMAGE, String DESC) {
        this.ID = ID;
        this.IMAGE = IMAGE;
        this.DESC = DESC;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }

    public String getDESC() {
        return DESC;
    }

    public void setDESC(String DESC) {
        this.DESC = DESC;
    }
}
