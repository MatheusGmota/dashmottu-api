package br.com.dashmottu.controller.web.moto;

import br.com.dashmottu.model.dto.MotoRequestDTO;
import br.com.dashmottu.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller("webMotoController")
@RequestMapping("/motos")
public class MotoController {

    @Autowired
    public MotoService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("motos", service.listarTodos());
        return "motos/listar";
    }

    @GetMapping("/nova")
    public String novaMoto(Model model) {
        model.addAttribute("moto", new MotoRequestDTO());
        return "motos/formulario";
    }

    @PostMapping()
    public String salvar(@Valid @ModelAttribute("moto") MotoRequestDTO entidade, BindingResult result) {
        if (result.hasErrors())
            return "motos/formulario";
        service.salvar(entidade);
        return "redirect:/motos";
    }

    @GetMapping("delete/{id}")
    public String deletar(@PathVariable("id") Long id, Model model){
        service.deletar(id);
        return "redirect:/motos";
    }

    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) throws Exception {
        model.addAttribute("moto", service.obterPorId(id));
        return "motos/formularioEdit";
    }

    @PostMapping("atualizar/{id}")
    public String atualizar(@PathVariable("id") Long id, @Valid @ModelAttribute("moto") MotoRequestDTO entidade, BindingResult result){
        if (result.hasErrors())
            return "motos/formularioEdit";
        service.editar(id, entidade);
        return "redirect:/motos";
    }
}
