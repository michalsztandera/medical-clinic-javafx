# 🏥 Medical Clinic Management System (JavaFX)

Projekt desktopowej aplikacji do zarządzania kliniką medyczną — zbudowany w **JavaFX**, z integracją **MySQL**, obsługą użytkowników, logowaniem oraz dynamicznym panelem administracyjnym.

---

## 🚧 Panel Administracyjny — *w trakcie budowy*

🔧 **Trwają prace nad nowoczesnym panelem administracyjnym**, który umożliwi zarządzanie:

- ✅ Listą użytkowników (Pacjenci, Lekarze, Rejestratorzy, Administratorzy)
- ✅ Dodawaniem, edytowaniem i usuwaniem kont
- ✅ Rejestrowaniem logów systemowych (kto co zrobił)
- ✅ Konfiguracją ustawień systemowych (SMTP, przypomnienia, eksport logów, godziny pracy)
- 🟡 Nowoczesny i responsywny UI (JavaFX + CSS)
- 🛠️ Planowana przyszła integracja z e-mail/SMS oraz powiadomieniami

![Status](https://img.shields.io/badge/Admin%20Panel-WIP-yellow)

---

## ✨ Główne funkcje aplikacji

- 🔒 **Logowanie** z uwzględnieniem ról użytkownika
- 🗓️ **Zarządzanie wizytami** – dodawanie, edytowanie, podgląd
- 🧑‍⚕️ **Zarządzanie pacjentami i lekarzami**
- 📊 **System logów** – rejestrowanie akcji użytkowników
- 💌 **Moduł powiadomień (SMTP)** – *w przygotowaniu*
- 🎨 **Nowoczesny interfejs** oparty na stylach CSS i ikonach FontAwesome

---

## 🖥️ Technologie

- Java 22 + JavaFX 24
- MySQL 8+
- JDBC (z obsługą wyjątków i testem połączenia)
- Maven (planowane)
- Ikony: FontAwesomeFX / Ikonli
- CSS 3.0 (dla stylizacji komponentów JavaFX)

---

## 🛠️ Jak uruchomić?

1. Upewnij się, że masz zainstalowane:
   - Java 22+
   - JavaFX SDK 24
   - MySQL + baza `medical-clinic`
2. Skonfiguruj połączenie z bazą danych w pliku `DBUtil.java`
3. Uruchom klasę `MainApp.java`

---

## 📌 Status projektu

- [x] Podstawowa architektura aplikacji
- [x] Logowanie + Role
- [x] Moduł pacjentów i lekarzy
- [x] System logów
- [ ] Panel administratora 🛠️ (w budowie)
- [ ] SMTP / powiadomienia
- [ ] Wersja produkcyjna (.exe / .jar)

---

## 📬 Kontakt

Projekt prowadzony przez [Michał Sztandera](https://github.com/michalsztandera)

---
