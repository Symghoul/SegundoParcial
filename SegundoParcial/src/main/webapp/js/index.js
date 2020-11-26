const inputTarea = document.getElementById('inputTarea');
const tareaBtn = document.getElementById('tareaBtn');
const columnsContainer = document.getElementById('columnsContainer');
const toDoContainer = document.getElementById('toDoContainer');
const doingContainer = document.getElementById('doingContainer');
const doneContainer = document.getElementById('doneContainer');

const registrar = ()=>{
    let tareaObj = {
        id:0,
        texto: inputTarea.value,
        fecha: 0,
        tipo: 1
        };

    let xhr = new XMLHttpRequest();
    xhr.addEventListener('readystatechange', ()=>{
        if(xhr.readyState === 4)
            console.log(xhr.responseText);
            getAllTareasE();
    });
    xhr.open('POST', 'http://localhost:8080/SegundoParcial/api/tareas/create');
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(tareaObj));
};



tareaBtn.addEventListener('click', registrar);

const repaint = () =>{
    let xhr = new XMLHttpRequest();
    xhr.addEventListener('readystatechange', ()=>{
        if(xhr.readyState === 4){
            alert('Estoy pintando¡¡')
            let json = xhr.responseText;
            let response = JSON.parse(json);
            toDoContainer.innerHTML ='';
            doingContainer.innerHTML ='';
            doneContainer.innerHTML ='';
            toDoContainer.innerHTML ='To Do';
            doingContainer.innerHTML ='Doing';
            doneContainer.innerHTML ='Done';
            for(let i=0; i<response.length;i++){
                let tareaDTO = response[i];
                let view = new tareaView(tareaDTO);
                if(tareaDTO.tipo===1){               
                    toDoContainer.appendChild(view.render());
                }
                else if(tareaDTO.tipo===2){
                    
                    doingContainer.appendChild(view.render());
                }else if(tareaDTO.tipo===3){
                    
                    doneContainer.appendChild(view.render());
                }
            }
        }
    });
    xhr.open('GET', 'http://localhost:8080/SegundoParcial/api/tareas/all');
    xhr.send();
}


const getAllTareasE = () =>{
    let xhr = new XMLHttpRequest();
    xhr.addEventListener('readystatechange', ()=>{
        if(xhr.readyState === 4){
            let json = xhr.responseText;
            let response = JSON.parse(json);
            toDoContainer.innerHTML ='';
            doingContainer.innerHTML ='';
            doneContainer.innerHTML ='';
            toDoContainer.innerHTML ='To Do';
            doingContainer.innerHTML ='Doing';
            doneContainer.innerHTML ='Done';
            for(let i=0; i<response.length;i++){
                let tareaDTO = response[i];
                let view = new tareaView(tareaDTO);
                if(tareaDTO.tipo===1){
                    
                    toDoContainer.appendChild(view.render());
                    view.onDeleteFinish = ()=>{
                        if(toDoContainer.contains(document.getElementById('tarea'+tareaDTO.id)))
                            toDoContainer.removeChild(document.getElementById('tarea'+tareaDTO.id));
                    };
                }
                else if(tareaDTO.tipo===2){
                    
                    doingContainer.appendChild(view.render());
                    view.onDeleteFinish = ()=>{
                        if(doingContainer.contains(document.getElementById('tarea'+tareaDTO.id)))
                            doingContainer.removeChild(document.getElementById('tarea'+tareaDTO.id));
                    };
                }else if(tareaDTO.tipo===3){
                    
                    doneContainer.appendChild(view.render());
                    view.onDeleteFinish = ()=>{
                        if(doneContainer.contains(document.getElementById('tarea'+tareaDTO.id)))
                            doneContainer.removeChild(document.getElementById('tarea'+tareaDTO.id));
                    };
                }
            }
            repaint;
        }
        
    });
    xhr.open('GET', 'http://localhost:8080/SegundoParcial/api/tareas/all');
    xhr.send();
};
getAllTareasE();