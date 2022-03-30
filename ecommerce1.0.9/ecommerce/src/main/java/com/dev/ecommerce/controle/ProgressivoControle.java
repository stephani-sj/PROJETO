package com.dev.ecommerce.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.dev.ecommerce.modelos.Compra;
import com.dev.ecommerce.modelos.ItensCompra;
import com.dev.ecommerce.modelos.ItensProgressivo;
import com.dev.ecommerce.modelos.Produto;
import com.dev.ecommerce.modelos.Progressivo;
import com.dev.ecommerce.repositorios.ItensCompraRepositorio;
import com.dev.ecommerce.repositorios.ItensProgressivoRepositorio;
import com.dev.ecommerce.repositorios.ProdutoRepositorio;
import com.dev.ecommerce.repositorios.ProgressivoRepositorio;


@Controller
public class ProgressivoControle {
	
	@Autowired
	private ProdutoRepositorio repositorioProduto;
	
	@Autowired
	private ProgressivoRepositorio repositorioProgressivo;
	
	@Autowired
	private ItensProgressivoRepositorio repositorioItensProgressivo;
	
	
	private List<ItensProgressivo> itensProgressivo = new ArrayList<ItensProgressivo>();
	private Progressivo progressivo = new Progressivo();
	
	@GetMapping("/progressivo/cadastrar")
	public ModelAndView chamarPercentual() {
		ModelAndView mv = new ModelAndView("administrativo/promocoes/progressivo/cadastro");	
		mv.addObject("progressivo", progressivo);
		mv.addObject("listaItensProgressivo", itensProgressivo);
		return mv;
	}
	
	@GetMapping("/adicionarProgressivo/{id}")
	public String adicionarProgressivo(@PathVariable Long id) {

		Optional<Produto> prod = repositorioProduto.findById(id);
		Produto produto = prod.get();

		int controle = 0;
		for (ItensProgressivo it : itensProgressivo) {
			if (it.getProduto().getProId().equals(produto.getProId())) {
				controle = 1;
				break;
			}
		}
		if (controle == 0) {
			ItensProgressivo item = new ItensProgressivo();
			item.setProduto(produto);
			itensProgressivo.add(item);

		}
		return "redirect:/progressivo/cadastrar";
	}
	
	@GetMapping("/progressivo")
	public ModelAndView finalizarProgressivo() {
		ModelAndView mv = new ModelAndView("administrativo/home");
		repositorioProgressivo.saveAndFlush(progressivo);
		for (ItensProgressivo c : itensProgressivo) {
			c.setProgressivo(progressivo);
			repositorioItensProgressivo.saveAndFlush(c);
		}
		itensProgressivo = new ArrayList<>();
		progressivo = new Progressivo();
		return mv;
	}
}
