# 🔐 Web Application Penetration Testing Project

## 📌 Overview
This project demonstrates **manual penetration testing** performed on a Java (Spring Boot) web application.

The goal was to identify real-world vulnerabilities in:
- Authentication
- Session Management
- Access Control
- API Security

---

## 🛠 Tools Used
- Burp Suite
- Browser DevTools
- Manual Testing

---

## 🎯 Scope
- Login functionality
- Session handling (JSESSIONID)
- Role-based access (Admin/User)
- API endpoints
- Document access

---

## 🚨 Vulnerabilities Identified

| # | Vulnerability | Severity |
|--|-------------|---------|
| 1 | Default Credentials | 🔴 High |
| 2 | Session Management Issue | 🟠 Medium |
| 3 | Broken Access Control | 🔴 High |
| 4 | Privilege Escalation | 🔴 High |
| 5 | IDOR | 🟠 Medium |

---

# 🔍 1. Default Credentials

## Description
The application allows login using default credentials:
- admin / admin

## Impact
Unauthorized users can gain admin access.

## Proof of Concept

![Default Login](screenshots/login-default-credentials.png)

---

# 🍪 2. Session Management Issue (JSESSIONID)

## Description
Multiple sessions are created for the same user. Old sessions are not invalidated.

## Impact
Session hijacking and reuse of old sessions.

## Proof of Concept

![Session Cookie](screenshots/session-jsessionid.png)

---

# 🔓 3. Broken Access Control (Session Hijacking)

## Description
An attacker can reuse an admin session ID to access restricted endpoints.

## Steps
1. Login as normal user
2. Replace JSESSIONID with admin session
3. Access admin endpoints


---

# 🔄 4. Privilege Escalation via API

## Description
The API `/api/update-user-role` allows role modification without proper authorization.

## Impact
User can change role to Admin.

## Proof of Concept

![Privilege Escalation](screenshots/privilege-escalation-api.png)

---

# 📂 5. Insecure Direct Object Reference (IDOR)

## Description
Changing `ReferenceID` in the URL allows access to other users' documents.

## Example
Once access the document with http://localhost:8081/document-view?ReferenceID=802, change the id to 803 or 804
