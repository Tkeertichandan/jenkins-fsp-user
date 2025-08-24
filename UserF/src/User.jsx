import React, { useEffect, useState } from "react";
import axios from "axios";

export default function User() {
  const [users, setUsers] = useState([]);
  const [formData, setFormData] = useState({
    id: "",
    name: "",
    gender: "",
    email: "",
    contact: ""
  });
  const [editMode, setEditMode] = useState(false);

  const baseUrl = "http://localhost:2030/springbootuserapi/user"; 

  // Fetch all users
  const fetchUsers = async () => {
    try {
      const res = await axios.get(`${baseUrl}/viewAllUsers`);
      setUsers(res.data);
    } catch (err) {
      console.error("Error fetching users:", err);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  // Handle input change
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  // Add or Update User
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (editMode) {
        await axios.put(`${baseUrl}/updateUser`, formData);
        alert("User updated successfully");
      } else {
        await axios.post(`${baseUrl}/adduser`, formData);
        alert("User added successfully");
      }
      setFormData({ id: "", name: "", gender: "", email: "", contact: "" });
      setEditMode(false);
      fetchUsers();
    } catch (err) {
      console.error("Error saving user:", err);
    }
  };

  // Edit User
  const handleEdit = (user) => {
    setFormData(user);
    setEditMode(true);
  };

  // Delete User
  const handleDelete = async (id) => {
    try {
      await axios.delete(`${baseUrl}/deleteUser`, { params: { id } });
      alert("User deleted successfully");
      fetchUsers();
    } catch (err) {
      console.error("Error deleting user:", err);
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>User Management</h2>

      {/* Form */}
      <form onSubmit={handleSubmit} style={{ marginBottom: "20px" }}>
        <input
          type="number"
          name="id"
          placeholder="ID"
          value={formData.id}
          onChange={handleChange}
          required
          disabled={editMode}
        />
        <input
          type="text"
          name="name"
          placeholder="Name"
          value={formData.name}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="gender"
          placeholder="Gender"
          value={formData.gender}
          onChange={handleChange}
          required
        />
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={formData.email}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="contact"
          placeholder="Contact"
          value={formData.contact}
          onChange={handleChange}
          required
        />
        <button type="submit">{editMode ? "Update User" : "Add User"}</button>
      </form>

      {/* Users Table */}
      <table border="1" cellPadding="8" style={{ width: "100%" }}>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Gender</th>
            <th>Email</th>
            <th>Contact</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {users.length > 0 ? (
            users.map((u) => (
              <tr key={u.id}>
                <td>{u.id}</td>
                <td>{u.name}</td>
                <td>{u.gender}</td>
                <td>{u.email}</td>
                <td>{u.contact}</td>
                <td>
                  <button onClick={() => handleEdit(u)}>Edit</button>
                  <button onClick={() => handleDelete(u.id)}>Delete</button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="6">No users found</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}
