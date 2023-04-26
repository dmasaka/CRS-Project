import React, { useEffect, useState } from 'react'
import styles from '../Admin.module.css'
import { BasNav } from './BasNav'
import { Button, Form } from 'react-bootstrap'

export const Report = () => {
  const [studs, setStuds] = useState([])
  const [studid, setStudid] = useState(0)
  const [grades, setGrades] = useState([])
  useEffect(() => {
    fetch('http://localhost:8080/students/all')
      .then(resp => resp.json())
      .then(data => setStuds(data))
  }, [setStuds])


  const catchem = e => {
    fetch('http://localhost:8080/cr/student/' + studid)
      .then(resp => resp.json())
      .then(data => { setGrades(data) })
    e.preventDefault()
  }
  return (
    <>
      <BasNav />
      <div className={styles.Container}>
        <div className={styles.SerCont}>
          <div>
            <p>Generate Report Card</p>
          </div>
          <Form className={styles.FormSin}>
            <Form.Label>Student Id</Form.Label>
            <Form.Select aria-label="Professor Selection" placeholder='Choose Student' defaultValue={0} onChange={e => setStudid(e.target.value)}>
              <option disabled={true} value={0}>Choose Student ...</option>
              {studs.map(item => (
                <option value={item.userId} key={item.userId}>{item.username}</option>
              ))}
            </Form.Select>
            <Button variant="primary" type="submit" onClick={catchem}>
              Submit
            </Button>
          </Form>
        </div>
        <div className={styles.Grade}>
          <table>
            <tbody>
              <tr>
                <th>Course</th>
                <th>Grade</th>
              </tr>
              {grades.map(item => (
                <tr key={item.coursecode}>
                  <td>{item.coursecode}</td>
                  <td>{item.grade}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </>
  )
}
