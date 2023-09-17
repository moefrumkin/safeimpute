import { useEffect, useRef, useState } from "react"

type SummaryData = {
  nearest: number | null;
  mean: number | null;
  median: number | null;
  high: number | null;
  low: number | null;
}

const Summary = () => {

  const [summaryData, setSummaryData] = useState<SummaryData>({
    "nearest": null,
    "mean": null,
    "median": null,
    "high": null,
    "low": null,
  })

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
        console.log("floor posted successfully: ", response)
        await getSummaryData()
      }
    } catch (error) {
      console.log("floor posted unsuccessfully", error)
    }
  }


  const getSummaryData = async () => {
    try {
      const response = await fetch("http://localhost:8080/summaryStats")
      if (response.ok) {
        console.log("successfully fetched nearest", response)
        const data = await response.json()
        console.log("data", data)
        setSummaryData(data)
      }
    } catch(error) {
      console.log("error fatching nearest:", error)
    }
  }

 




  return (
    <div className="bg-primary border border-background/20 rounded-sm max-w-6xl p-4 text-secondary">
      <div className=" font-semibold text-2xl">
        Summary
      </div>
      <div className="opacity-20 text-m">
          Analyze your data.
      </div>
      <div className="grid grid-cols-3">
        <div>
          <div className="p-4">
            <div className="m-2">
              Nearest: {summaryData.nearest}
            </div>
            <div className="flex m-2 align-middle left-0 truncate">
              <p className="self-center ">Floor</p>
              <input ref={floorRef} className="ml-6 p-1 rounded-sm bg-white/10 border w-1/2 left-0 "></input>
              <button className="left-0 border bg-black/20 border-white/50 p-1 ml-6 rounded-sm transition duration-200 hover:bg-black/30" onClick={handleClick}>confirm</button>
            </div>
            <div className="opacity-20 text-m pl-2">The lowest accuracy the summary statistics will include.</div>
          </div>

        </div>
        <div>
          <div className="p-4">
            <div className="m-2">
              Mean: {summaryData.mean}
            </div>
            <div className="m-2">
              Median: {summaryData.median}
            </div>
          </div>
        </div>
        <div>
          <div className="p-4">
              <div className="m-2">
                High: {summaryData.high}
              </div>
              <div className="m-2">
                Low: {summaryData.low}
              </div>
          </div>
        </div>
      </div>



      <div></div>

    </div>
  )
}


export default Summary