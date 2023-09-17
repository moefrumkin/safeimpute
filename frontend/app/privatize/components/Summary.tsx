import { useEffect, useRef, useState } from "react"

type SummaryData = {
  nearest: string | null;
  mean: string | null;
  median: string | null;
  high: string | null;
  low: string | null;
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
        setSummaryData({
          "nearest": (data.nearest * 100).toFixed(2) + "%",
          "mean": (data.mean * 100).toFixed(2) + "%",
          "median": (data.median * 100).toFixed(2) + "%",
          "high": (data.high * 100).toFixed(2) + "%",
          "low": (data.low * 100).toFixed(2) + "%"
        });
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
            <p className="font-semibold">Nearest % Noise to Point: </p> {summaryData.nearest}
            </div>
            <div className=" m-2 align-middle left-0 truncate">
              <p className="self-center font-semibold">Floor</p>
              <input ref={floorRef} className="mt-1 p-1 rounded-sm bg-white/10 border w-1/2 left-0 "></input>
              <button className="left-0 border bg-black/20 border-white/50 p-1 ml-6 rounded-sm transition duration-200 hover:bg-black/30" onClick={handleClick}>confirm</button>
            </div>
            <div className="opacity-20 text-m pl-2">The lowest accuracy the summary statistics will include.</div>
          </div>

        </div>
        <div>
          <div className="p-4">
            <div className="m-2">
            <p className="font-semibold">Mean: </p> {summaryData.mean}
            </div>
            <div className="m-2">
            <p className="font-semibold">Median: </p> {summaryData.median}
            </div>
          </div>
        </div>
        <div>
          <div className="p-4">
              <div className="m-2 ">
              <p className="font-semibold">High: </p> {summaryData.high}
              </div>
              <div className="m-2">
                <p className="font-semibold">Low: </p>{summaryData.low}
              </div>
          </div>
        </div>
      </div>



      <div></div>

    </div>
  )
}


export default Summary