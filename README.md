# 🏷 Mini Inventory Management

## 📌 프로젝트 소개
Vue + Spring Boot 기반 재고 관리 시스템

## 🛠 기술 스택
- Frontend: Vue 3
- Backend: Spring Boot
- DB: MariaDB / H2
- Deploy: AWS EC2 + Nginx

## 🚀 주요 기능
- 상품 CRUD
- 재고 증감 트랜잭션 처리
- 대시보드 실시간 재고 현황 반영 - polling 방식 사용
- JWT 로그인

## 📷 실행 화면
1. 창고 배치도
<img width="1154" height="859" alt="image" src="https://github.com/user-attachments/assets/00bde117-c1af-489c-ab18-f9d7f8f41e43" />
2. 대시보드
<img width="1280" height="591" alt="image" src="https://github.com/user-attachments/assets/ac5d5c2e-31b4-4d26-a333-124f43a91e7e" />
3. 입/출고 등록
<img width="1201" height="348" alt="image" src="https://github.com/user-attachments/assets/681784a7-6c59-4086-a02b-2a923d405224" />
4. 재고 이력
<img width="1149" height="747" alt="image" src="https://github.com/user-attachments/assets/ccad9e32-2d00-42e2-8aa0-30e9b9aaed58" />

## 📷 작업 화면
1. 대시보드
2. 창고 배치드
3. 제품 등록
4. 재고 조회
5. 입/출고 등록
6. 재고 이력

## 📂 프로젝트 구조
# Backend 폴더 구조
org.myinventory.backend  <br/>
 ├─ config  <br/>
 ├─ controller  <br/>
 ├─ entity  <br/>
 ├─ repository  <br/>
 ├─ service  <br/>
 └─ BackendApplication  <br/>

# Frontend 폴더 구조 
src  <br/>
 ├─ assets  <br/>
 │   ├─ css  <br/>
 │   └─ js  <br/>
 ├─ components  <br/>
 │   ├─ Dashboard.vue  <br/>
 │   ├─ Header.vue  <br/>
 │   ├─ ItemAdd.vue  <br/>
 │   ├─ ItemList.vue  <br/>
 │   ├─ Login.vue  <br/>
 │   ├─ StockHistory.vue  <br/>
 │   ├─ StockManagement.vue  <br/>
 │   └─ WarehouseMap.vue  <br/>
 ├─ App.vue  <br/>
 └─ main.js  <br/>

# 데모 사이트 /계정
 <br/>
사이트 주소 :15.165.204.186
데모 유저 정보  id : admin pw : admin102030!!
