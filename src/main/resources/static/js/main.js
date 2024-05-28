const deleteButtons = document.querySelectorAll(".delete-btn")
console.log(deleteButtons) 

deleteButtons.forEach((btn)=>{
    btn.addEventListener('click',(e)=>{
       if( !window.confirm("Voulez-vous vraiment effectuer cette suppression?")){
            e.preventDefault()
       }
        
    })
    
})


