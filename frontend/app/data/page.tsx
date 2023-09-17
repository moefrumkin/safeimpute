import Download from "./components/Download"
import Upload from "./components/Upload"




const Data = () => {



  return (

    <div className="h-screen w-screen bg-foreground">
      <div className="grid grid-cols-2 gap-6 pt-12 max-w-6xl mx-auto">
        <Upload />
        <Download />
      </div>


    </div>
  )
}



export default Data