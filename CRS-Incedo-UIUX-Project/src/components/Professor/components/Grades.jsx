import React, { useEffect, useState } from 'react'
import styles from '../Professor.module.css'
import { BasNav } from './BasNav'
import { Button, Form } from 'react-bootstrap'
import { userstore } from '../../../redux/store'

export const Grades = () => {
    const [coursec, setCoursec] = useState('')
    const [courses, setCourses] = useState([])
    const [refresh, setRefresh] = useState(false)
    const [studentidc, setStudentidc] = useState('')
    const [studs, setStuds] = useState([])
    const [gradec, setGradec] = useState('')
    const [regs, setRegs] = useState([{ 'coursecode': 'FUN111', 'studentid': 2333, 'grade': 'A+' }])
    useEffect(() => {
        const nstore = userstore.getState()
        fetch('http://localhost:8080/cr/professor/' + nstore.userid)
            .then(resp => resp.json())
            .then(data => setRegs(data))
    }, [setRegs, refresh])

    useEffect(() => {
        setCourses(regs.map(item => item.coursecode).filter((val, ind, arr) => arr.indexOf(val) === ind))
    }, [regs])

    useEffect(() => {
        setStuds(regs.filter(item => item.coursecode === coursec).map(item => item.studentid))
    }, [coursec])
    
    

    const changegrade = e => {
        e.preventDefault()
        const old = regs.filter(item => item.coursecode === coursec && item.studentid == studentidc)[0]
        const ncr = {...old, grade: gradec}
        fetch('http://localhost:8080/cr/add', {
            method: "PUT",
            headers: new Headers({
                'Content-Type': 'application/json'
            }),
            body: JSON.stringify(ncr)
        })
        .then(setRefresh(!refresh))
    }

    return (
        <>
            <BasNav />
            <div className={styles.Container}>
                <div className={styles.SerCont}>
                    <div className={styles.Grade}>
                        <p>Add Grade</p>
                        <Form className={styles.FormSin}>
                            <Form.Label>Course</Form.Label>
                            <Form.Select aria-label="Course Selection" placeholder='Choose Course' defaultValue={0} onChange={e => setCoursec(e.target.value)}>
                                <option disabled={true} value={0}>Choose Course ...</option>
                                {courses.map(item =>(
                                    <option value={item} key={item}>{item}</option>
                                ))}
                            </Form.Select>
                            <Form.Label>Student Id</Form.Label>
                            <Form.Select aria-label="Student Id Selection" placeholder='Choose Student Id' defaultValue={0} onChange={e => setStudentidc(e.target.value)}>
                                <option disabled={true} value={0}>Choose Student Id ...</option>
                                {studs.map(item =>(
                                    <option value={item} key={item}>{item}</option>
                                ))}
                            </Form.Select>
                            <Form.Group className="mb-3" controlId="formBasicText">
                                <Form.Label>Grade</Form.Label>
                                <Form.Control type="text" placeholder="Enter Grade" onChange={e => setGradec(e.target.value)} value={gradec} />
                            </Form.Group>
                            <Button variant="primary" type="submit" onClick={changegrade}>
                                Submit
                            </Button>
                        </Form>
                    </div>
                    <div className={styles.Regs}>
                        <table>
                            <tbody>
                                <tr>
                                    <th>Course</th>
                                    <th>Student Id</th>
                                    <th>Grade</th>
                                </tr>
                                {regs.map(item => (
                                    <tr key={item.coursecode+item.studentid}>
                                        <td>{item.coursecode}</td>
                                        <td>{item.studentid}</td>
                                        <td>{item.grade}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </>
    )
}
