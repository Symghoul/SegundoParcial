class tareaView{

    constructor(tarea){
        this.tarea = tarea;
        this.onDeleteFinish = null;
    }

    deleteTarea = () =>{

        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{

            if(xhr.readyState===4){
                var response = JSON.parse(xhr.responseText);
                if(response.message === 'Tarea eliminada con exito'){
                    if(this.onDeleteFinish!==null) this.onDeleteFinish();
                }else{
                    alert('No se pudo eliminar el profesor');
                }
            }
        });
        xhr.open('DELETE', 'http://localhost:8080/SegundoParcial/api/tareas/delete/'+this.tarea.id);
        xhr.send();
    }

    goToNext = ()=>{

        let obj = {
            id:this.tarea.id,
            texto:this.tarea.texto,
            fecha:this.tarea.fecha,
            tipo:this.tarea.tipo+1
            
        }

        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{

            if(xhr.readyState===4){
                var response = JSON.parse(xhr.responseText);
                if(response.message === 'Cambio realizado con exito'){
                    if(this.onDeleteFinish!==null) this.onDeleteFinish();
                }else{
                    alert('No se pudo modificar la tarea');
                }
            }
        });
        
        xhr.open('PUT', 'http://localhost:8080/SegundoParcial/api/tareas/edit/');
        xhr.setRequestHeader('Content-Type','application/json');
        xhr.send(JSON.stringify(obj));
    }

    goToLast = () =>{

        let obj = {
            id:this.tarea.id,
            texto:this.tarea.texto,
            fecha:this.tarea.fecha,
            tipo:this.tarea.tipo-1
            
        }

        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{

            if(xhr.readyState===4){
                var response = JSON.parse(xhr.responseText);
                if(response.message === 'Cambio realizado con exito'){
                    if(this.onDeleteFinish!==null) this.onDeleteFinish();
                }else{
                    alert('No se pudo modificar la tarea');
                }
            }
        });
        
        xhr.open('PUT', 'http://localhost:8080/SegundoParcial/api/tareas/edit/');
        xhr.setRequestHeader('Content-Type','application/json');
        xhr.send(JSON.stringify(obj));
    }


    render = ()=>{
        let component = document.createElement('div');
        component.id = 'tarea'+this.tarea.id;
        component.className = 'tareaComponent';
        let texto = document.createElement('p');
        let fecha = document.createElement('small');
        let delBtn = document.createElement('button');
        let nextBtn = document.createElement('button');
        let lastBtn = document.createElement('button');

        if(this.tarea.tipo===1){
            lastBtn.hidden=true;
        }
        else if(this.tarea.tipo===3){
            nextBtn.hidden = true;
        }


        delBtn.className = 'deleteT';
        nextBtn.className = 'nextT';
        lastBtn.className = 'lastT';

        delBtn.innerHTML = 'X';
        nextBtn.innerHTML = '>>';
        lastBtn.innerHTML = '<<';

        texto.innerHTML = this.tarea.texto;
        fecha.innerHTML = this.tarea.fecha;

        component.appendChild(texto);
        component.appendChild(fecha);
        component.appendChild(delBtn);
        component.appendChild(nextBtn);
        component.appendChild(lastBtn);

        //Comportamiento
        delBtn.addEventListener('click', this.deleteTarea);
        nextBtn.addEventListener('click', this.goToNext);
        lastBtn.addEventListener('click', this.goToLast);



        return component;
    }
}