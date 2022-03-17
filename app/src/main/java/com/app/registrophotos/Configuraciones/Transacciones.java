package com.app.registrophotos.Configuraciones;

public class Transacciones {
    //Nombre de la base de datos
    public static final String DATE_BASE = "dbPhotos";
    //Tabla donde se registraran las imagenes
    public static final String TABLE_PHOTO = "tbPhotos";

    //Datos a guardar en la tabla
    public static final String ID = "id";
    public static final String IMAGE = "imagen";
    public static final String DESCRIPCION = "descripcion";

    //Crear la base de datos
    public static final String CREATE_TABLE_PHOTO ="CREATE TABLE "+TABLE_PHOTO+" (id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                                    "imagen LONGBLOB, descripcion TEXT)";

    //Elimina tablas
    public static final String DROP_TABLE_PHOTOS = "DROP TABLE IF EXISTS " + TABLE_PHOTO;

    //Consultar Tabla Photos
    public static final String SELECT_TABLE_PHOTOS = "SELECT * FROM "+TABLE_PHOTO;

    //Eliminar un registro de la tabla tbPhotos
    public static final String DELETE_REGISTRO = "DELETE FROM "+ TABLE_PHOTO +" WHERE id = ";
}
