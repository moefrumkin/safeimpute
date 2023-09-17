
'use client'


const Download = () => {

  const downloadHandler = async () => {

    try {
      const response = fetch("http://localhost:8080/download")
      console.log("loading")
    } catch (error) {
      console.log(error)
    }
  }



  return (

    <div className="bg-primary border border-background/20 rounded-sm max-w-6xl p-4 text-secondary">
      <div className="font-semibold text-2xl">
        Download
      </div>


      <div className="opacity-20 text-md py-2">
          Download your file below
      </div>

      
      <a href="http://localhost:8080/download" download>
        <button onClick={downloadHandler} className="left-0 border bg-black/20 border-white/50 p-2  rounded-sm transition duration-200 hover:bg-black/30">Click Me</button>
      </a>

    </div>

  )

}


export default Download