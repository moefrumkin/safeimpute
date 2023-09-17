'use client'

import React, { PureComponent, useEffect, useState } from 'react';
import { AreaChart, Area, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';





export default function Chart({ chartData }) {



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

    <XAxis dataKey="x" />
    <YAxis />
    
    <Tooltip />
    <Area type="linear" dataKey="y" stroke="#82ca9d" fillOpacity={1} fill="url(#colorPv)" strokeWidth="3"/>


  </AreaChart>
  )
}