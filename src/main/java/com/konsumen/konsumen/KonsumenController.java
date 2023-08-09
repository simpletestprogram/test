package com.konsumen.konsumen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
public class KonsumenController {
    @Autowired
    private KonsumenService konsumenService;

    @GetMapping("/konsumen")
    public String showKonsumenList(Model model){
        List<Konsumen> konsumenList = konsumenService.listAll();
        model.addAttribute("konsumenList", konsumenList);
        return "konsumen";
    }

    @GetMapping("/konsumen/tambahkonsumen")
    public String showFormTambahKonsumen(Model model){
        model.addAttribute("konsumen", new Konsumen());
        model.addAttribute("pageTitle", "Tambah Konsumen");
        return "konsumen_form";
    }

    @PostMapping("/konsumen/save")
    public String saveKonsumen(Konsumen konsumen, RedirectAttributes redirectAttributes){
        konsumen.setTglRegistrasi(new Date());
        konsumen.setStatus("aktif");
        konsumenService.save(konsumen);
        redirectAttributes.addFlashAttribute("message", "Konsumen berhasil disimpan");
        return "redirect:/konsumen";
    }

    @GetMapping("/konsumen/edit/{id}")
    public String showFormEditKonsumen(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) throws Exception {
        Konsumen konsumen = konsumenService.get(id);
        model.addAttribute("konsumen", konsumen);
        model.addAttribute("pageTitle", "Edit Konsumen");
        return "konsumen_form";
    }

    @GetMapping("/konsumen/delete/{id}")
    public String deleteKonsumen(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) throws Exception {
        konsumenService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Konsumen berhasil dihapus");
        return "redirect:/konsumen";
    }

    @RequestMapping(path = {"/","/search"})
    public String cariKonsumen(Model model, String keyword) throws Exception {
        List<Konsumen> konsumenList = konsumenService.findKonsumen(keyword);
        model.addAttribute("konsumenList", konsumenList);
        return "konsumen";
    }

}
