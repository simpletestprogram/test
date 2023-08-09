package com.konsumen.konsumen;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KonsumenRepository extends CrudRepository<Konsumen, Integer> {
    public Long countById(Integer id);

    @Query("SELECT konsumen FROM Konsumen konsumen WHERE CONCAT(konsumen.nama, ' ', konsumen.alamat, ' ', konsumen.kota, ' ', konsumen.provinsi) LIKE %?1%")
    public List<Konsumen> search(String keyword);
}
