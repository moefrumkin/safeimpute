'use client'
import { useEffect, useState } from "react"




const Post = () => {

  // const [data, setData] = useState<string | null>(null);

  // useEffect(() => {
  //   const fetchYeo = async () => {
  //     try {
  //       const response = await fetch("http://localhost:8080/yeo")
  //       if (response.ok) {
  //         const text = await response.text()
  //         setData(text)
  //       } 
  //     } catch (error) {
  //       console.log("error fetching backend", error)
  //     } 
  //   }
  //   fetchYeo()
  // }, [])

  const onClickHandler = async () => {
    try {
      const response = await fetch (
        "http://localhost:8080/parametersUpload",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            "parameter1": "test1",
            "parameter2": "test2"
            }
          )
        }
      )
      if (response.ok) {
        console.log("data posted successfully")
      }
    } catch (error) {
      console.log("error posting backend", error)
    }
  }

  return (
    <>
      <button onClick={onClickHandler} className="text-white">
        post

      </button>
    </>
  )
}

export default Post