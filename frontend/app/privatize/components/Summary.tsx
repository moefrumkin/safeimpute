import { useEffect, useRef, useState } from "react"



const Summary = () => {
  const [floor, setFloor] = useState(null)
  const [nearest, setNearest] = useState<string>('')

  const floorRef = useRef<HTMLInputElement>(null);

  const handleClick = async ()  => {

    const floorData = {"floorValue": floorRef.current?.value}
    try {
      const response = await fetch(
        "http://localhost:8080/parametersUploadFloorValue",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(floorData)
        }
      )
      if (response.ok) {
        const data = await response.json()
        setFloor(data)
        console.log("floor posted successfully")
      }
    } catch (error) {
      console.log("floor posted unsuccessfully", error)
    }
  }


  useEffect(() => {
    const getNearest = async () => {
      try {
        const data = await fetch("http://localhost:8080/nearest")
        if (data.ok) {
          const text = await data.text()
          setNearest(text)
        }
      } catch(error) {
        console.log("error fatching nearest:", error)
      }
    }
    getNearest()
  }, [])

  return (
    <div className="bg-primary border border-background/20 rounded-sm max-w-6xl p-4 text-secondary">
      <div className=" font-semibold text-2xl">
        Summary
      </div>
      <div className="opacity-20 text-m">
          Analyze your data.
      </div>
      <div className="p-4">
        <div className="m-2">
          Best: {}
        </div>
        <div className="m-2">
          Nearest: {nearest}
        </div>
      </div>
      <div className="flex m-2 align-middle w-1/3 left-0 truncate">
        <p className="self-center ">Floor</p>
        <input ref={floorRef} className="ml-6 p-1 rounded-sm bg-white/10 border w-1/2 left-0 "></input>
        <button className="left-0 border bg-black/20 border-white/50 p-1 ml-6 rounded-sm transition duration-200 hover:bg-black/30" onClick={handleClick}>confirm</button>
      </div>

    </div>
  )
}


export default Summary