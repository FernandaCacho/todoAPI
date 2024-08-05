let url = "http://localhost:8080/tasks";

function editTask() {
   const savedId = sessionStorage.getItem('editIdTask');
   const taskTitle = document.getElementById('editTaskTitle').value;
   const taskDescription = document.getElementById('editTaskDescription').value;
   const taskCompleted = document.getElementById('editTaskCompleted').value;
   console.log(savedId, taskTitle, taskCompleted, taskDescription);

   if (taskTitle.trim() === '') {
       alert('Necessario informar um titulo');
       return;
   }

   const dados = {
      id: savedId,
      title: taskTitle,
      description: taskDescription,
      completed: taskCompleted === 'true'
   };
  
  putAPI(url, dados);
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
}