package com.konsumen.konsumen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class KonsumenService {
    @Autowired private KonsumenRepository konsumenRepository;

    public List<Konsumen> listAll(){
        return (List<Konsumen>) konsumenRepository.findAll();
    }

    public void save(Konsumen konsumen){
        konsumenRepository.save(konsumen);
    }

    public Konsumen get(Integer id) throws Exception{
        Optional<Konsumen> optionalKonsumen = konsumenRepository.findById(id);
        if(optionalKonsumen.isPresent()){
            return optionalKonsumen.get();
        }
        throw new Exception("Tidak dapat menemukan ID " + id);
    }

    public void delete(Integer id) throws Exception {
        Long count = konsumenRepository.countById(id);
        if(count == null || count == 0){
            throw new Exception("Tidak dapat menemukan ID " + id);
        }
        konsumenRepository.deleteById(id);
    }

    public List<Konsumen> findKonsumen(String keyword){
        if(keyword != null){
            return konsumenRepository.search(keyword);
        }
        return(List<Konsumen>) konsumenRepository.findAll();
    }
}
