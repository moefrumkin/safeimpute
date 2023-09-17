import { useEffect, useState } from "react"



const Summary = () => {
  const [nearest, setNearest] = useState<string>('')

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

    </div>
  )
}


export default Summary