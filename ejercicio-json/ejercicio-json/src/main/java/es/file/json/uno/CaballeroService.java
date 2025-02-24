package es.file.json.uno;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CaballeroService {
    ObjectMapper objectMapper;
    String path = "src/main/resources/caballeros.json";
    File file;
    List<Caballero> listCaballero;

    public CaballeroService() {
        objectMapper = new ObjectMapper();
        file = new File(path);
        loadAll();
    }

    public Caballero findById(int id) {
        Caballero caballero = new Caballero(id);
        int posicion = listCaballero.indexOf(caballero);
        if( posicion < 0){
            return null;
        }
        return listCaballero.get(posicion);
    }

    public List<Caballero> findByDateRange(String startDate, String endDate) {
        if (startDate.isEmpty() || endDate.isEmpty()){
            return null;
        }
        List<Caballero> caballeros = new ArrayList<>();
        
        return null;
    }

    public List<Caballero> getList() {
        return listCaballero;
    }
    
    public List<Caballero> loadAll() {
        try {
            listCaballero = objectMapper.readValue(file, new TypeReference<List<Caballero>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCaballero;
    }
    
    public boolean add(Caballero caballero) {
        if (caballero == null){
            return false;
        }
        int posicion = listCaballero.indexOf(caballero);
        if (posicion >= 0){
            return false;
        }
        boolean insertar = listCaballero.add(caballero);
        if (insertar){
            saveFile(file, listCaballero);
        }
        return insertar;
    }
            
    public void saveFile(File file, List<Caballero> caballeros) {
        try {
            objectMapper.writeValue(file, caballeros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
            
    public boolean delete(Caballero caballero) {
        if (caballero == null){
            return false;
        }
        boolean borrar = listCaballero.remove(caballero);
        if ( borrar){
            saveFile(file, listCaballero);
        }
        return borrar;
    }
        
    
}
