package br.com.dashmottu.controller.web.patio;

import br.com.dashmottu.model.dto.PatioDTO;
import br.com.dashmottu.service.PatioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller("webPatioController")
@RequestMapping("/patios")
public class PatioController {

    @Autowired
    public PatioService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("patios", service.listarTodos());
        return "patios/listar";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        model.addAttribute("patio", new PatioDTO());
        return "patios/formulario";
    }

    @PostMapping()
    public String salvar(@Valid @ModelAttribute("patio") PatioDTO entidade, BindingResult result) {
        entidade.setImagemPlantaUrl("https://http.cat/images/800.jpg");
        if (result.hasErrors())
            return "patios/formulario";
        service.salvar(entidade);
        return "redirect:/patios";
    }

    @GetMapping("delete/{id}")
    public String deletar(@PathVariable("id") Long id, Model model){
        service.deletar(id);
        return "redirect:/patios";
    }

    @GetMapping("editarEndereco/{id}")
    public String editar(@PathVariable("id") Long id, Model model) throws Exception {
        model.addAttribute("patio", service.obterPorId(id));
        return "patios/editEndereco";
    }

    @PostMapping("atualizar/{id}")
    public String atualizar(@PathVariable("id") Long id, @Valid @ModelAttribute("patio") PatioDTO entidade, BindingResult result){
        if (result.hasErrors())
            return "patios/formularioEdit";
        service.editar(id, entidade);
        return "redirect:/patios";
    }
}
