'use client'

import React, { PureComponent } from 'react';
import { AreaChart, Area, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';


type ChartProps = {
  chartVals: number;
};


export default function Chart({ chartVals }: ChartProps) {


  const data = []
  for (let i = 0; i < chartVals; i++){
    
    const uv_rand = Math.floor(Math.random() * 5000)
    const pv_rand = Math.floor(Math.random() * 5000)


    data.push(
      {
        name: 'Page G',
        uv: uv_rand,
        pv: pv_rand,
      }
    )
  }







  return (
  <AreaChart width={500} height={250} data={data}
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

    <XAxis dataKey="name" />
    <YAxis />
    
    <Tooltip />
    <Area type="linear" dataKey="pv" stroke="#82ca9d" fillOpacity={1} fill="url(#colorPv)" strokeWidth="3"/>
    <Area type="linear" dataKey="uv" stroke="#8884d8" fillOpacity={1} fill="url(#colorUv)" strokeWidth="3"/>


  </AreaChart>)
}