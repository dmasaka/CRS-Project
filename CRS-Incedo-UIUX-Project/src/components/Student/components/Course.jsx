import React, { useEffect, useState } from 'react'
import styles from '../Student.module.css'
import { BasNav } from './BasNav'
import { Button, Form } from 'react-bootstrap'
import { userstore } from '../../../redux/store'

export const Course = () => {
    const nstore = userstore.getState()
    const [course, setCourse] = useState('')
    const [able, setAble] = useState([])
    const [refresh, setRefresh] = useState(false)
    const [courses, setCourses] = useState([{ "coursecode": "FUN111", "grade": "A+" }, { "coursecode": "MATH20", "grade": "F+" }])

    useEffect(() => {
        console.log('useeffect course')
        const fetchem = async () => {
            const nstore = userstore.getState()
            const crs = await (await fetch( import.meta.env.VITE_BACK + 'cr/student/' + nstore.userid)).json()
            setCourses(crs)
            const cable = await (await fetch( import.meta.env.VITE_BACK + 'course/all')).json()
            setAble(cable)
        }
        fetchem()
    }, [setCourses, setAble, refresh])

    const add = e => {
        e.preventDefault();
        fetch( import.meta.env.VITE_BACK + 'cr/add', {
            method: "POST",
            headers: new Headers({
                'Content-Type': 'application/json'
            }),
            body: JSON.stringify({
                coursecode: course,
                studentid: nstore.userid,
                grade: "None"
            })
        })
        .then(
            setTimeout(() => {
                setRefresh(!refresh)
            }, 2000)
        )
    }

    const dele = e => {
        e.preventDefault()
        courses.forEach(item => {
            if (item.coursecode === course) {
                fetch( import.meta.env.VITE_BACK + 'cr/delete/' + item.courseregid, { method: "DELETE" })
                .then(
                    setCourses(prev => ([...prev.slice(0,prev.findIndex(item => item.coursecode == course)), ...prev.slice(prev.findIndex(item => item.coursecode == course) + 1)]))
                )
            }
        })
    }

    return (
        <>
            <BasNav />
            <div className={styles.Container}>
                <div className={styles.SerCont}>
                    <div className={styles.CourseCont}>
                        <div>
                            <Form className={styles.FormSin}>
                                <Form.Select aria-label="Course Selection" defaultValue={0} placeholder='Choose course' onChange={e => setCourse(e.target.value)}>
                                    <option disabled={true} value={0} key={"soup"}>Choose Course ...</option>
                                    {able.map(item => (
                                        <option value={item.courseCode} key={item.courseCode}>{item.courseCode}</option>
                                    ))}
                                </Form.Select>
                                <div className={styles.ButSpace}>
                                    <Button variant="success" type="submit" onClick={add}>
                                        Add
                                    </Button>
                                    <Button variant="danger" type="submit" onClick={dele}>
                                        drop
                                    </Button>
                                </div>
                            </Form>
                        </div>
                    </div>
                    <div className={styles.Grade}>
                        <table>
                            <tbody>
                                <tr>
                                    <th>Course</th>
                                    <th>Grade</th>
                                </tr>
                                {courses.map(item => (
                                    <tr key={item.coursecode}>
                                        <td>{item.coursecode}</td>
                                        <td>{item.grade}</td>
                                    </tr>
                                ))}
                                {/* <tr>
                                <th>Course</th>
                                {courses.map(item => (<td>{item.coursecode}</td>))}
                                </tr>
                                <tr>
                                <th>Grade</th>
                                {courses.map(item => (<td>{item.grade}</td>))}
                                </tr> */}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </>
    )
}
