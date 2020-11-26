package model.provider;

import db.MySQLConection;
import entity.Tarea;
import model.dto.TareaDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class TareaProvider {

    //Proveer informacion

    public ArrayList<TareaDTO> getAlltareas() {
        Date now = new Date();

        ArrayList<TareaDTO> tareaDTOS = new ArrayList<>();
        MySQLConection conection = new MySQLConection();
        try {
            String sql = "SELECT id, texto, fecha, tipo FROM tareas";
            ResultSet resultSet = conection.query(sql);

            while (resultSet.next()) {
                TareaDTO tarea = new TareaDTO();
                tarea.setId(resultSet.getInt(resultSet.findColumn("id")));
                tarea.setTexto(resultSet.getString(resultSet.findColumn("texto")));
                long timestamp = resultSet.getLong(resultSet.findColumn("fecha"));
                now.setTime(timestamp);
                tarea.setFecha(now.toString());
                tarea.setTipo(resultSet.getInt(resultSet.findColumn("tipo")));
                tareaDTOS.add(tarea);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        conection.disconnect();
        return  tareaDTOS;
    }

    public TareaDTO getTareaById(String id) {
        Date now = new Date();

        Tarea tarea = new Tarea();
        MySQLConection conection = new MySQLConection();
        try {
            String sql = "SELECT id, texto, fecha, tipo FROM tareas WHERE id=" + id;
            ResultSet resultSet = conection.query(sql);

            while (resultSet.next()) {
                tarea.setId(resultSet.getInt(resultSet.findColumn("id")));
                tarea.setTexto(resultSet.getString(resultSet.findColumn("texto")));
                long timestamp = resultSet.getLong(resultSet.findColumn("fecha"));
                now.setTime(timestamp);
                tarea.setFecha(now.toString());
                tarea.setTipo(resultSet.getInt(resultSet.findColumn("tipo")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        conection.disconnect();
        return mapToDTO(tarea);
    }


    //Proveer acciones
    public void insertTareas(Tarea tareaE) {
        Date now = new Date();
        long timestamp = now.getTime();
        MySQLConection conection = new MySQLConection();
        String sql = "INSERT INTO tareas(texto, fecha, tipo) VALUES('$texto','$fecha','$tipo') ";
        sql = sql.replace("$texto", tareaE.getTexto());
        sql = sql.replace("$fecha", "" + timestamp);
        sql = sql.replace("$tipo", "" + tareaE.getTipo());
        conection.executeSQL(sql);
    }

    public boolean editTarea(TareaDTO tareaDTO) {

        MySQLConection conection = new MySQLConection();
        String sql = "UPDATE tareas SET tipo = '$tipo' WHERE id=$id";
        sql = sql.replace("$id", ""+tareaDTO.getId());
        sql = sql.replace("$tipo", "" + tareaDTO.getTipo());
        boolean success = conection.executeSQL(sql);
        return  success;
    }

    public boolean deleteTareas(String id) {
        MySQLConection conection = new MySQLConection();
        String sql = "DELETE FROM tareas WHERE id="+id;
        return conection.executeSQL(sql);
    }

    public Tarea mapFromDTO(TareaDTO tareaDTO) {

        Tarea tarea = new Tarea();
        tarea.setId(tareaDTO.getId());
        tarea.setFecha(tareaDTO.getFecha());
        tarea.setTexto(tareaDTO.getTexto());
        tarea.setTipo(tareaDTO.getTipo());
        return tarea;
    }

    public TareaDTO mapToDTO(Tarea tarea) {
        TareaDTO tareaDTO = new TareaDTO();
        tareaDTO.setId(tarea.getId());
        tareaDTO.setFecha(tarea.getFecha());
        tareaDTO.setTexto(tarea.getTexto());
        tareaDTO.setTipo(tarea.getTipo());
        return tareaDTO;
    }


}
