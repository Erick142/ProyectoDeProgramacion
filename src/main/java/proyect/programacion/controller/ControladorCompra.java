package proyect.programacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import proyect.programacion.model.Compra;
import proyect.programacion.model.Pedido;
import proyect.programacion.repository.RepoCompra;
import proyect.programacion.repository.RepoPedido;
import proyect.programacion.repository.RepoProducto;
import proyect.programacion.repository.RepoUsuario;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class ControladorCompra {
    @Autowired
    private RepoProducto repoProducto;
    @Autowired
    private RepoCompra repoCompra;
    @Autowired
    private RepoUsuario repoUsuario;
    @Autowired
    private RepoPedido repoPedido;
    @PostMapping("/comprar")
    public String comprar(@RequestBody HashMap compras, HttpSession session, Model model){
        if (session.getAttribute("usuarioEmail")==null){
            return "redirect:login";
        }
        String id=compras.get("ids").toString();
        String cantidad=compras.get("cantidades").toString();
        List<Integer> ids=hacerLista(id);
        List<Integer> cantidades=hacerLista(cantidad);
        List<Pedido> pedidos=new ArrayList<>();
        int precioTotal=0;
        for (int i=0;ids.size()>i;i++){
            precioTotal+=repoProducto.findById(ids.get(i)).get().getPrecio()*cantidades.get(i);
            Pedido pedido=new Pedido(repoProducto.findById(ids.get(i)).get(),cantidades.get(i));
            repoPedido.save(pedido);
            repoProducto.findById(ids.get(i)).get().setStock(repoProducto.findById(ids.get(i)).get().getStock()-pedido.getCantidad());
            pedidos.add(pedido);
        }
        Compra compra=new Compra(pedidos,repoUsuario.findById(session.getAttribute("usuarioEmail").toString()).get(),precioTotal);
        repoCompra.save(compra);
        model.addAttribute("micompra",compra);
        session.setAttribute("carrito",new HashMap<Integer,Integer>());
        return "misCompras";
    }
    private List<Integer> hacerLista(String string){
        string=string.replace(" ","");
        string=string.replace("[","");
        string=string.replace("]","");
        String stringSplit[]=string.split(",");
        List<Integer> lista=new ArrayList();
        for (String s:stringSplit){
            lista.add(Integer.parseInt(s));
        }
        return  lista;
    }
    @GetMapping("/misCompras")
    public String misCompras(Model model,HttpSession session){
        if (session.getAttribute("usuarioEmail")==null){
            return "redirect:login";
        }model.addAttribute("misCompras",repoCompra.findAllByUsuario(repoUsuario.findById(session.getAttribute("usuarioEmail").toString()).get()));
        return "misCompras";
    }
}
