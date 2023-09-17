'use client'

import React, { PureComponent, useEffect, useState } from 'react';
import { AreaChart, Area, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';


type ChartProps = {
  chartVals: number;
};


export default function Chart({ chartVals }: ChartProps) {

  const [chartData, setChartData] = useState<{x: number, y: number}[]>([])


  useEffect(() => {
    const getChartData = async () => {
      try {
        const response = await fetch("http://localhost:8080/points")
        if (response.ok) {
          const data = await response.json()
          const dataArray = Object.entries(data).map(([x, y]) => ({x, y}))
          setChartData(dataArray)
          console.log(dataArray)
        }
      } catch (error) {
        console.log("error fetching chart data", error)
      }
    }

    getChartData()


  })


      // {
      //   name: 'Page G',
      //   uv: uv_rand,
      //   pv: pv_rand,
      // }
   







  return (
  <AreaChart width={500} height={250} data={chartData}
    margin={{ top: 10, right: 60, left: 0, bottom: 0 }}>

    <defs>

      <linearGradient id="colorUv" x1="0" y1="0" x2="0" y2="1">
        <stop offset="5%" stopColor="#8884d8" stopOpacity={0.8}/>
        <stop offset="95%" stopColor="#8884d8" stopOpacity={0}/>
      </linearGradient>
      <linearGradient id="colorPv" x1="0" y1="0" x2="0" y2="1">
        <stop offset="5%" stopColor="#82ca9d" stopOpacity={0.8}/>
        <stop offset="95%" stopColor="#82ca9d" stopOpacity={0}/>
      </linearGradient>
    </defs>
    <CartesianGrid strokeDasharray="" stroke='#bfbfbf'/>

    <XAxis dataKey="x" />
    <YAxis />
    
    <Tooltip />
    <Area type="linear" dataKey="y" stroke="#82ca9d" fillOpacity={1} fill="url(#colorPv)" strokeWidth="3"/>


  </AreaChart>
  )
}