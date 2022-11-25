//añadir al carro
const botones=document.getElementsByClassName("carro");
for (let boton of botones){
    boton.addEventListener("click",async function (){
        json={"id":boton.value}
        var resultado=await fetch("/añadircarro",{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(json)
        }).then(respuesta=>respuesta.json())
        console.log(resultado);

        if (resultado.code==0){
            let toast=new bootstrap.Toast(document.getElementById("myToast"))
            document.getElementById("toastMensaje").innerHTML=resultado.nombre+" fue añadido al carrito!";
            toast.show();
        }
        if (resultado.code==1){
            let toast=new bootstrap.Toast(document.getElementById("myToast"))
            document.getElementById("toastMensaje").innerHTML=resultado.nombre+" fue añadido al carrito otra vez!";
            toast.show();
        }
        if (resultado.code==3){
            let toast=new bootstrap.Toast(document.getElementById("myToast"))
            document.getElementById("toastMensaje").innerHTML=resultado.nombre+" ya seleccionaste lo maximo disponible";
            toast.show();
        }

    })
}