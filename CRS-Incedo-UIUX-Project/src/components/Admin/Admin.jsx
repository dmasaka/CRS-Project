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
                <div className={styles.SerCont}>
                    <div>
                        <p>Generate Report Card</p>
                        <button>Generate</button>
                    </div>
                    <div>
                        <p>Add Professor</p>
                        <div className={styles.FormT}>
                            <label htmlFor="coursecode">Course</label>
                            <input type="text" name="coursecode" onChange={e => setcoursecode(e.target.value)} value={coursecode} />
                            <label htmlFor="profid">Professor Id</label>
                            <input type="number" name="profid" onChange={e => setProfid(e.target.value)} value={profid} />
                        </div>
                        <button>Add</button>
                    </div>
                    <div>
                        <p>Approve Student Registration</p>
                        <div className={styles.FormT}>
                            <label htmlFor="studid">Student id</label>
                            <input type="number" name="studid" onChange={e => setStudid(e.target.value)} value={studid} />
                        </div>
                        <div>
                            <button>Approve</button>
                            <br />
                            <button>Approval All</button>
                        </div>
                    </div>
                    <div>
                        <p>Add Course</p>
                        <div className={styles.FormT}>
                            <label htmlFor="coursec">Course</label>
                            <input type="text" name="coursec" onChange={e => setCoursec(e.target.value)} value={coursec} />
                            <label htmlFor="namec">Name</label>
                            <input type="text" name="namec" onChange={e => setNamec(e.target.value)} value={namec} />
                            <div>
                                <input type="checkbox" name="isoffered" onChange={e => setIsoffered(!isoffered)} checked={isoffered} />
                                <label htmlFor="isoffered">Offered</label>
                            </div>
                            <label htmlFor="profc">Professor Id</label>
                            <input type="number" name="profc" onChange={e => setProfc(e.target.value)} value={profc} />
                        </div>
                        <button>Add</button>
                    </div>
                    <div>
                        <p>Remove Course</p>
                        <div className={styles.FormT}>
                            <label htmlFor="coursed">Course</label>
                            <input type="text" name="coursed" onChange={e => setCoursed(e.target.value)} value={coursed} />
                        </div>
                        <button>Remove</button>
                    </div>
                </div>
            </div>
        </>
    )
}
