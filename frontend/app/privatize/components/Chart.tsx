'use client'

import React, { PureComponent, useEffect, useState } from 'react';
import { AreaChart, Area, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';





export default function Chart({ chartData }) {
  let minX, maxX, minY, maxY;



  useEffect(() => {
    const getChartData = async () => {

    }

    getChartData()


  }, [])


      // {
      //   name: 'Page G',
      //   uv: uv_rand,
      //   pv: pv_rand,
      // }

      if (chartData.length !== 0) {
        console.log(chartData)

         minX = parseFloat(chartData[0].x);
         maxX = parseFloat(chartData[0].x);
         minY = parseFloat(chartData[0].y);
         maxY = parseFloat(chartData[0].y);
        
        for (let i = 1; i < chartData.length; i++) {
          let currentX = parseFloat(chartData[i].x);
          let currentY = chartData[i].y;
        
          if (currentX < minX) {
            minX = currentX;
          }
          if (currentX > maxX) {
            maxX = currentX;
          }
          if (currentY < minY) {
            minY = currentY;
          }
          if (currentY > maxY) {
            maxY = currentY;
          }
        }
        minX = minX.toFixed(2)
        maxX = maxX.toFixed(2)
        minY = minY.toFixed(2)
        maxY = maxY.toFixed(2)
  
        console.log(minX,maxX,minY,maxY)


      }
  







  return (
  <AreaChart width={650} height={250} data={chartData}
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

    <XAxis domain={[minX, maxX]} dataKey="x" />
    <YAxis domain={[minY, maxY]}/>
    
    <Tooltip />
    <Area type="linear" dataKey="y" stroke="#82ca9d" fillOpacity={1} fill="url(#colorPv)" strokeWidth="3"/>


  </AreaChart>
  )
}