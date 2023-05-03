import React, { useState } from 'react'
import styles from './Admin.module.css'
import { BasNav } from './components/BasNav'
export const Admin = () => {
    const [coursecode, setcoursecode] = useState('')
    const [profid, setProfid] = useState('')
    const [studid, setStudid] = useState('')
    const [coursec, setCoursec] = useState('')
    const [namec, setNamec] = useState('')
    const [isoffered, setIsoffered] = useState(true)
    const [profc, setProfc] = useState('')
    const [coursed, setCoursed] = useState('')
    return (
        <>
            <BasNav />
            <div className={styles.Container}>
                <h1>Admin Dashboard</h1>
            </div>
        </>
    )
}
