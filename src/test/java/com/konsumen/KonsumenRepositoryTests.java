package com.konsumen;

import com.konsumen.konsumen.Konsumen;
import com.konsumen.konsumen.KonsumenRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class KonsumenRepositoryTests {

    @Autowired
    private KonsumenRepository konsumenRepository;

    @Test
    public void testAddNew(){
        Konsumen konsumen = new Konsumen();
        konsumen.setNama("Raymond");
        konsumen.setAlamat("Pulo Gebang Permai Blok A2/12");
        konsumen.setKota("Jakarta Timur");
        konsumen.setProvinsi("Jawa");
        konsumen.setTglRegistrasi(new Date());
        konsumen.setStatus("aktif");

        Konsumen savedKonsumen = konsumenRepository.save(konsumen);

        Assertions.assertThat(savedKonsumen).isNotNull();
        Assertions.assertThat(savedKonsumen.getId()).isGreaterThan(0);
    }

}
