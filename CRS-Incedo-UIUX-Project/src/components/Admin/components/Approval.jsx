import React, { useEffect, useState } from 'react'
import styles from '../Admin.module.css'
import { BasNav } from './BasNav'
import { Button, Form } from 'react-bootstrap'
import { useNavigate } from 'react-router-dom'

export const Approval = () => {
  const [unapprove, setUnapprove] = useState([])
  const [refresh, setRefresh] = useState(true)
  const navi = useNavigate()
  const approveone = e => {
    console.log(e.target.id)
    fetch('http://localhost:8080/students/approve/' + e.target.id, { method:"POST" })
    navi()
    setRefresh(!refresh)
  }
  const approveall = e => {
    fetch('http://localhost:8080/students/approveall', { method:"POST" })
    setRefresh(!refresh)
  }

  useEffect(() => {
    console.log('useeffect')
    fetch('http://localhost:8080/students/unapproved')
      .then(resp => resp.json())
      .then(data => setUnapprove(data))
  }, [setUnapprove, refresh])
  return (
    <>
      <BasNav />
      <div className={styles.Container}>
        <div className={styles.SerCont}>
          <div>
            <p>Approve Student Registration</p>
            <table>
              <tbody>
                <tr>
                  <th>Name</th>
                  <th>Username</th>
                  <th></th>
                </tr>
                {
                  unapprove.map(item => (
                    <tr key={item.userId}>
                      <td>{item.name}</td>
                      <td>{item.username}</td>
                      <td>
                        <Button variant='success' type='submit' onClick={approveone} id={item.userId}>Approve</Button>
                      </td>
                    </tr>
                  ))
                }
              </tbody>
            </table>
            <Button variant="danger" type="submit" onClick={approveall}>
              Approve All
            </Button>
          </div>
        </div>
      </div>
    </>
  )
}
