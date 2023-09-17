'use client'
import { useEffect, useState } from "react"




const Call = () => {

  const [data, setData] = useState<string | null>(null);

  useEffect(() => {
    const fetchYeo = async () => {
      try {
        const response = await fetch("http://localhost:8080/yeo")
        if (response.ok) {
          const text = await response.text()
          setData(text)
        } 
      } catch (error) {
        console.log("error fetching backend", error)
      } 
    }
    fetchYeo()
  }, [])


  return (
    <>
      <div className="text-white">
        {data ? data : "loading..."}

      </div>
    </>
  )
}

export default Call