import React, { useEffect, useState } from 'react'
import styles from '../Admin.module.css'
import { BasNav } from './BasNav'
import { Button, Form } from 'react-bootstrap'

export const Course = () => {
  const [coursec, setCoursec] = useState('')
  const [namec, setNamec] = useState('')
  const [isoffered, setIsoffered] = useState(true)
  const [profc, setProfc] = useState('')
  const [courses, setCourses] = useState([])
  const [refresh, setRefresh] = useState(true)
  const [profs, setProfs] = useState([])
  useEffect(() => {
    fetch('http://localhost:8080/course/all')
      .then(resp => resp.json())
      .then(data => setCourses(data))
  }, [setCourses, refresh])

  useEffect(() => {
    fetch('http://localhost:8080/professors/all')
      .then(resp => resp.json())
      .then(data => setProfs(data))
  }, [setProfs])


  const dele = e => {
    console.log(e.target.id)
    fetch('http://localhost:8080/course/delete/' + e.target.id, { method: "DELETE" })
    setRefresh(!refresh)
  }

  const adder = e => {
    e.preventDefault()
    console.log("is offered ", isoffered)
    fetch('http://localhost:8080/course/add',{
      method:"POST",
      headers: new Headers({
        'Content-Type': 'application/json'
      }),
      body: JSON.stringify({
        "courseCode":coursec,
        "name":namec,
        "professorId":profc,
        "offered":isoffered
      })
    })
    .then(resp => resp.json())
    .then(data => console.log(data))
  }

  return (
    <>
      <BasNav />
      <div className={styles.Container}>
        <div className={styles.SerCont}>
          <div>
            <p>Add Course</p>
            <Form className={styles.FormSin}>
              <Form.Group className="mb-3" controlId="formBasicText">
                <Form.Label>Course</Form.Label>
                <Form.Control type="text" placeholder="Enter course code" onChange={e => setCoursec(e.target.value)} value={coursec} />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicText">
                <Form.Label>Name</Form.Label>
                <Form.Control type="text" placeholder="Enter name" onChange={e => setNamec(e.target.value)} value={namec} />
              </Form.Group>
              <Form.Label>Professor Id</Form.Label>
              <Form.Select aria-label="Professor Selection" defaultValue={0} placeholder='Choose professor' onChange={e => setProfc(e.target.value)}>
                <option disabled={true} value={0}>Choose Professor ...</option>
                {profs.map(item => (
                  <option value={item.userId} key={item.userId}>{item.username}</option>
                ))}
              </Form.Select>
              <Form.Group className="mb-3">
                <Form.Check
                  type="checkbox"
                  label="Offered"
                  onChange={e => setIsoffered(!isoffered)}
                  checked={isoffered}
                />
              </Form.Group>
              <Button variant="primary" type="submit" onClick={adder}>
                Add
              </Button>
            </Form>
          </div>
          <div>
            <p>Remove Course</p>
            <table>
              <tbody>
                <tr>
                  <th>Name</th>
                  <th>Professor Id</th>
                </tr>
                {
                  courses.map(item => (
                    <tr key={item.courseCode}>
                      <td>{item.courseCode}</td>
                      <td>{item.professorId}</td>
                      <td>
                        <Button variant='danger' type='submit' onClick={dele} id={item.courseCode}>Remove</Button>
                      </td>
                    </tr>
                  ))
                }
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </>
  )
}
