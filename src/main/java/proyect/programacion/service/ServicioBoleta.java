package proyect.programacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyect.programacion.model.Boleta;
import proyect.programacion.model.Pedido;
import proyect.programacion.model.ProductoDetalle;
import proyect.programacion.model.Usuario;
import proyect.programacion.repository.RepoBoleta;
import proyect.programacion.repository.RepoProductoDetalle;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class ServicioBoleta{
    @Autowired
    private RepoBoleta repoBoleta;
    @Autowired
    private RepoProductoDetalle repoProductoDetalle;


    public Boleta guardar(Boleta boleta){
        return repoBoleta.save(boleta);
    }

    public Boleta nuevaBoleta(Pedido p){
        Boleta boleta = new Boleta(p);
        repoBoleta.save(boleta);
        p.setBoleta(boleta);
        return boleta;
    }



    public void reemplazarBoleta(Pedido p,Boleta boletaNueva){
        Boleta boleta= repoBoleta.findBoletaByPedido(p);
        boleta.setFecha(boletaNueva.getFecha());
        boleta.setPedido(boletaNueva.getPedido());
        boleta.setTotal(boletaNueva.getTotal());
        repoBoleta.deleteBoletaById(boleta.getId());
        repoBoleta.save(boletaNueva);
    }



    public Iterable<Boleta> verBoletas(){
        return repoBoleta.findAll();
    }

    public Optional<Boleta> encontrarPorId(Integer id){
        return repoBoleta.findById(id);
    }

    public void eliminarPorId(int id){
        repoBoleta.deleteBoletaById(id);
    }
    public Optional<Boleta> encontrarPorUsuario(Usuario usuario){
        return repoBoleta.findByUsuario(usuario);
    }


}




/*
    public Boleta nuevaBoleta(ProductoDetalle p, Usuario u){
        Boleta nuevaboleta = new Boleta();
        LocalDate fecha = LocalDate.now();
        nuevaboleta.setUsuario(u);
        nuevaboleta.setFecha(fecha);
        nuevaboleta.setPedido(p.getPedido());
        int cant=p.getCantidad();
        int precio=p.getProducto().getPrecio();
        nuevaboleta.setTotal(cant*precio);
        repoBoleta.guardar(nuevaboleta);
        return nuevaboleta;
    }

 */


//ProductoDetalle detalle=repoProductoDetalle.findProductoDetalleByPedido(p);
//Optional<ProductoDetalle> detalle =repoProductoDetalle.findByPedido(p);
// int cant=detalle.getCantidad();
//int precio=detalle.getProducto().getPrecio();
       /*
        nuevaboleta.setUsuario(u);
        nuevaboleta.setFecha(fecha);
        nuevaboleta.setPedido(p);
        nuevaboleta.setTotal(cant*precio);
        */