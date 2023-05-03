import React, { useEffect, useState } from 'react'
import styles from '../Student.module.css'
import { BasNav } from './BasNav'

export const Catalog = () => {
    const [courses, setCourses] = useState([])
    const [profs, setProfs] = useState({})
    
    useEffect(() => {
        let tempprofs = {}
        fetch(import.meta.env.VITE_BACK + 'professors/all')
            .then(resp => resp.json())
            .then(data => data.forEach(item => {
                tempprofs[item.userId] = item.name
            }))
            .then(() => fetch(import.meta.env.VITE_BACK + 'course/all')
            .then(resp => resp.json())
            .then(data => setCourses(data)))
        setProfs(tempprofs)
    }, [])
    

    return (
        <>
            <BasNav />
            <div className={styles.Container}>
                <div className={styles.SerCont}>
                    <div className={styles.GradeCourse}>
                        <div>
                            <p>Courses Available</p>
                        </div>
                        <table>
                            <tbody>
                                <tr>
                                    <th>Course</th>
                                    <th>Name</th>
                                    <th>Professor</th>
                                </tr>
                                {courses.map(item => (
                                    <tr key={item.courseCode}>
                                        <td>{item.courseCode}</td>
                                        <td>{item.name}</td>
                                        <td>{profs[item.professorId]}</td>
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
