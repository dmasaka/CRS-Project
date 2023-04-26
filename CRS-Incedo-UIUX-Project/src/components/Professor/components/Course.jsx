import React, { useEffect, useState } from 'react'
import styles from '../Professor.module.css'
import { BasNav } from './BasNav'
import { Button, Form } from 'react-bootstrap'
import { userstore } from '../../../redux/store'

export const Course = () => {
    const nstore = userstore.getState()
    const [regs, setRegs] = useState([])
    useEffect(() => {
        fetch('http://localhost:8080/cr/professor/' + nstore.userid)
            .then(resp => resp.json())
            .then(data => setRegs(data))
    }, [setRegs])

    return (
        <>
            <BasNav />
            <div className={styles.Container}>
                <div className={styles.SerCont}>
                    <div className={styles.Course}>
                        <p>Enrolled Students</p>
                        <div className={styles.Regs}>
                        <table>
                            <tbody>
                                <tr>
                                    <th>Professor Id</th>
                                    <th>Course</th>
                                    <th>Student Id</th>
                                </tr>
                                {regs.map(item => (
                                    <tr key={item.coursecode+item.studentid}>
                                        <td>{nstore.userid}</td>
                                        <td>{item.coursecode}</td>
                                        <td>{item.studentid}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                    </div>
                </div>
            </div>
        </>
    )
}



    // const [course, setCourse] = useState('')
    // const [courses, setCourses] = useState([])
    // useEffect(() => {
    //     fetch('http://localhost:8080/course/all')
    //         .then(resp => resp.json())
    //         .then(data => setCourses(data))
    // }, [])

    // const add = e => {
    //     e.preventDefault()
    //     const nstore = userstore.getState()
    //     let ocor = courses.filter(item => item.courseCode == course)[0]
    //     ocor.professorId = nstore.userid
    //     console.log(ocor)
    //     fetch('http://localhost:8080/course/add', {
    //         method: "POST",
    //         headers: new Headers({
    //             "Content-Type":"application/json"
    //         }),
    //         body: JSON.stringify(ocor)
    //     })
    // }