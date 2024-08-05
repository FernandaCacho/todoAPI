let url = "http://localhost:8080/tasks";

function showTasks(tasks) {
   const table = document.createElement('table');
   table.classList.add('table'); 

   const thead = document.createElement('thead');
   const headerRow = document.createElement('tr');

   const headers = ['Id', 'Titulo', 'Descricao', 'Status', 'Operacoes'];
   headers.forEach(headerText => {
     const th = document.createElement('th');
     th.scope = 'col';
     th.textContent = headerText;
     headerRow.appendChild(th);
   });

   thead.appendChild(headerRow);
   table.appendChild(thead);

   const tbody = document.createElement('tbody');

   tasks.forEach(task => {
     const status = task.completed ? "Concluido" : "Nao concluido";

     const row = document.createElement('tr');

     const idValue = document.createElement('td');
     idValue.textContent = task.id;
     row.appendChild(idValue);

     const titleValue = document.createElement('td');
     titleValue.textContent = task.title;
     row.appendChild(titleValue);

     const descriptionValue = document.createElement('td');
     descriptionValue.textContent = task.description;
     row.appendChild(descriptionValue);

     const statusValue = document.createElement('td');
     statusValue.textContent = status;
     row.appendChild(statusValue);

     const operations = document.createElement('td');
     const buttons = createTaskButtons(task);

     operations.appendChild(buttons);
     row.appendChild(operations);

     tbody.appendChild(row);
   });

   table.appendChild(tbody);

   const tasksElement = document.getElementById("tasks");
   tasksElement.innerHTML = '';
   tasksElement.appendChild(table);
}

function createTaskButtons(task) {
   const buttons = document.createElement('div');
   buttons.classList.add('btn-group');

   const completeButton = document.createElement('button');
   completeButton.type = 'submit';
   completeButton.classList.add('btn', 'btn-success');
   completeButton.textContent = 'Concluir';
   completeButton.addEventListener('click', () => completedTask(task));

   const editButton = document.createElement('button');
   editButton.type = 'button';
   editButton.classList.add('btn', 'btn-primary');
   editButton.textContent = 'Editar';
   editButton.addEventListener('click', () => {
      sessionStorage.setItem('editIdTask', task.id);
      window.location.href="editar-task.html"; 
   });

   const deleteButton = document.createElement('button');
   deleteButton.type = 'button';
   deleteButton.classList.add('btn', 'btn-danger');
   deleteButton.textContent = 'Excluir';
   deleteButton.addEventListener('click', () => deleteAPI(url, task.id));

   buttons.appendChild(completeButton);
   buttons.appendChild(editButton);
   buttons.appendChild(deleteButton);

   return buttons;
}

function addTask() {
   const taskTitle = document.getElementById('taskTitle').value;
   const taskDescription = document.getElementById('taskDescription').value;

   if (taskTitle.trim() === '') {
       alert('Necessario informar um titulo');
       return;
   }

   const dados = {
       title: taskTitle,
       description: taskDescription
   };

   postAPI(url, dados);
}

function completedTask(task) {
   const dados = {
      id: task.id,
      title: task.title, 
      description: task.description,
      completed: true
   }; 

   putAPI(url, dados);
}

async function getAPI(url){
   const response = await fetch (url, {method: 'GET'});

   var data = await response.json();
   console.log(data);

   if(response)
      showTasks(data);
}

async function getIdAPI(url){
   let urlId = url+"/"+id;

   const response = await fetch (urlId, {method: "GET"});

   var data = await response.json();
   console.log(data);

   if(response)
      showTasks(data);
}

async function postAPI(url, dados){
   const response = fetch(url, {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(dados)
    });

    if(response)
      getAPI(url);
}

async function putAPI(url, dados){
   console.log("putAPI");

   let urlId = url+'/'+dados.id;
   console.log(urlId);

   const response = fetch(urlId, {
      method: 'PUT',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(dados)
    });      

   //if(response)
   //   getAPI(url);
}

async function deleteAPI(url, id){
   let urlId = url+"/"+id;
   try {
      const response = await fetch(urlId, {method: 'DELETE'});
      if (!response.ok) {
        throw new Error('Erro ao deletar recurso');
      }
      getAPI(url);
      console.log('Recurso deletado com sucesso!');
    } catch (error) {
      console.error('Erro:', error);
    }
}

getAPI(url);