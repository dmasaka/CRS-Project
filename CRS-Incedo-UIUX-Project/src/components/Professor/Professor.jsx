import React, { useState } from 'react'
import styles from './Professor.module.css'
import { BasNav } from './components/BasNav'

export const Professor = () => {
  const [course, setCourse] = useState('')
  const [coursec, setCoursec] = useState('')
  const [studentidc, setStudentidc] = useState('')
  const [gradec, setGradec] = useState('')
  const [regs, setRegs] = useState([{ 'course': 'FUN111', 'studentid': 2333, 'grade': 'A+' }])
  return (
    <>
      <BasNav />
      <div className={styles.Container}>
        <div className={styles.SerCont}>
        </div>
      </div>
    </>
  )
}
