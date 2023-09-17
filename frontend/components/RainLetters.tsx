
'use client'

import { useRef, useEffect } from 'react'

const renderRain = (ref: React.RefObject<HTMLCanvasElement>) => {

  let canvas = ref.current
  if (!canvas) return

  let context = canvas.getContext("2d")
  if (!context) return

  canvas.width = window.innerWidth
  canvas.height = window.innerHeight


  const alphabet = "ATCG";


  const fontSize = 16;
  const columns = canvas.width / fontSize;

  const rainDrops: number[] = [];

  for (let x = 0; x < columns; x++) {
    rainDrops[x] = 1;
  }

  const render = () => {
    if (!context) return
    if (!canvas) return

    context.fillStyle = "rgba(0,0,0,0.05)"
    context.fillRect(0,0,canvas.width, canvas.height)

    context.fillStyle = "#f5f5f5"
    context.font = fontSize + "px monospace"

    for (let i = 0; i < rainDrops.length; i++) {
      const text = alphabet.charAt(
        Math.floor(Math.random() * alphabet.length)
      )
      context.fillText(text, i * fontSize, rainDrops[i] * fontSize)

      if (rainDrops[i]*fontSize > canvas.height && Math.random() > 0.975) {
        rainDrops[i] = 0
      }
      rainDrops[i]++
    }
  }
  return () => {
    setInterval(render, 100)
  }
}

const RainLetters = () => {
  const ref = useRef<HTMLCanvasElement>(null);
  // const keyName = "mrl-" + props.key
  // const thisClassNes = "mrl-container" + props.custom_class
  useEffect(() => renderRain(ref), [])

  return (
    <>
      <canvas  ref={ref}></canvas>
    </>
  )
}


export default RainLetters