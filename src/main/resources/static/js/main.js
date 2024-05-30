const deleteButtons = document.querySelectorAll(".delete-btn")

deleteButtons.forEach((btn)=>{
    btn.addEventListener('click',(e)=>{
       if( !window.confirm("Voulez-vous vraiment effectuer cette suppression?")){
            e.preventDefault()
       }
        
    })
    
})


