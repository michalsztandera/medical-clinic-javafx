# 🏥 Medical Clinic Management System (JavaFX)

**Projekt desktopowej aplikacji do zarządzania kliniką medyczną** — zbudowany z myślą o czytelnej architekturze, nowoczesnym UI i wygodnej obsłudze codziennej pracy personelu kliniki. Oparty o JavaFX, MySQL, oraz wzorce projektowe.

---

## 🚀 Główne funkcjonalności

### 🔒 System logowania z rolami
- Obsługa użytkowników: **Pacjent**, **Lekarz**, **Rejestrator**, **Administrator**
- Różne poziomy dostępu i interfejsy zależne od roli

### 👨‍⚕️ Zarządzanie pacjentami i lekarzami
- Dodawanie, edytowanie, usuwanie rekordów
- Podgląd danych kontaktowych i historii wizyt

### 🗓️ Obsługa wizyt
- Rejestracja wizyt przez rejestratora
- Podgląd nadchodzących konsultacji
- Historia wizyt dla pacjenta i lekarza

### 📜 System logów
- Rejestrowanie akcji użytkowników (kto, co, kiedy, na czym)
- Dostępne tylko dla administratora
- Możliwość eksportu do CSV

### 💌 Powiadomienia (SMTP)
- Konfiguracja danych SMTP w panelu admina
- Wysyłanie przypomnień o wizytach (planowane rozszerzenie o SMS)

---

## ⚙️ Panel Administracyjny (w budowie) 🚧

Nowoczesny panel administratora z dynamicznymi modułami:

- ✅ Zarządzanie użytkownikami:
  - Lista użytkowników (filtracja po rolach)
  - Dodawanie, edytowanie, usuwanie kont
- ✅ Systemowe logi i ich eksport
- ✅ Ustawienia systemowe:
  - SMTP (host, port, login, hasło)
  - Przypomnienia dla pacjentów (interwał, włącz/wyłącz)
  - Godziny pracy kliniki
- 🛠️ Zgłoszenia błędów i utrzymanie (moduł w fazie planowania)
- 📈 Statystyki systemowe (przyszła funkcjonalność)
- 🔧 Zarządzanie testowymi danymi i czyszczenie

---

## 🖥️ Technologie

- **Java 22**
- **JavaFX 24**
- **MySQL 8+**
- **JDBC** z obsługą wyjątków i testem połączenia
- **CSS 3.0** do stylizacji
- **Ikony**: FontAwesomeFX + Ikonli
- **Maven** (planowana migracja)

---

## 🧠 Architektura i wzorce projektowe

Projekt korzysta z popularnych wzorców:
- **Factory**
- **Singleton**
- **Adapter**
- **Facade**
- **Observer**
- **Command**

Zastosowano **modularną architekturę MVC**, ułatwiającą przyszły rozwój i testowanie.

---

## 📦 Jak uruchomić?

1. Zainstaluj:
   - Java 22+
   - JavaFX SDK 24
   - MySQL
2. Utwórz bazę `medical-clinic` i skonfiguruj dane w `DBUtil.java`
3. Uruchom `MainApp.java` w IntelliJ lub innym IDE
4. (Opcjonalnie) Skonfiguruj SMTP w ustawieniach admina

---

## 📌 Status projektu

| Moduł                          | Status             |
|-------------------------------|--------------------|
| Logowanie + Role              | ✅ Zakończono       |
| Pacjenci i Lekarze            | ✅ Zakończono       |
| Rejestracja Wizyt             | ✅ Zakończono       |
| System Logów                  | ✅ Zakończono       |
| Panel Administratora          | 🚧 W trakcie        |
| Moduł SMTP                    | 🚧 W trakcie        |
| Eksport logów do CSV          | ✅ Zakończono       |
| Panel użytkowników (admin)    | ✅ Zakończono       |
| Powiadomienia SMS             | 🔜 W planach        |
| Dashboard statystyk           | 🔜 W planach        |
| Wersja .JAR/.EXE              | 🔜 W planach        |

---

## 📬 Kontakt

**Autor:** Michał Sztandera  
📧 [michalsztandera@gmail.com](mailto:michalsztandera@gmail.com)  

---
