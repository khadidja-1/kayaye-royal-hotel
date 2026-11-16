const translations = {
    'en': {
        nav_dash: "Dashboard", nav_clients: "Clients", nav_rooms: "Rooms", nav_bookings: "Bookings", nav_employees: "Employees", nav_rest: "Restaurant", nav_house: "Housekeeping", nav_inv: "Inventory", nav_events: "Events",
        nav_website: "🌐 View Website", btn_logout: "Logout",
        title_clients: "Registered Clients", btn_add_client: "+ Add New Client", search: "Search by name...",
        th_photo: "Photo", th_name: "Full Name", th_contact: "Contact", th_email: "Email", th_account: "Account/ID", th_actions: "Actions",
        th_role: "Role", th_salary: "Salary", title_emp: "Staff Management", btn_add_emp: "Add Employee",
        welcome: "Welcome to the Manager Dashboard", card_clients: "Total Clients", card_rooms: "Available Rooms", card_bookings: "Total Bookings", card_revenue: "Total Revenue (FCFA)", card_stock: "Low Stock Alerts"
    },
    'fr': {
        nav_dash: "Tableau de Bord", nav_clients: "Clients", nav_rooms: "Chambres", nav_bookings: "Réservations", nav_employees: "Employés", nav_rest: "Restaurant", nav_house: "Ménage", nav_inv: "Inventaire", nav_events: "Événements",
        nav_website: "🌐 Voir le Site", btn_logout: "Déconnexion",
        title_clients: "Clients Enregistrés", btn_add_client: "+ Nouveau Client", search: "Rechercher par nom...",
        th_photo: "Photo", th_name: "Nom Complet", th_contact: "Contact", th_email: "Email", th_account: "Compte/ID", th_actions: "Actions",
        th_role: "Rôle", th_salary: "Salaire", title_emp: "Gestion du Personnel", btn_add_emp: "Ajouter un Employé",
        welcome: "Bienvenue sur le Tableau de Bord", card_clients: "Total Clients", card_rooms: "Chambres Libres", card_bookings: "Total Réservations", card_revenue: "Revenu Total (FCFA)", card_stock: "Alertes de Stock"
    },
    'ar': {
        nav_dash: "لوحة القيادة", nav_clients: "العملاء", nav_rooms: "الغرف", nav_bookings: "الحجوزات", nav_employees: "الموظفين", nav_rest: "المطعم", nav_house: "التنظيف", nav_inv: "المخزون", nav_events: "الفعاليات",
        nav_website: "🌐 عرض الموقع", btn_logout: "تسجيل الخروج",
        title_clients: "العملاء المسجلين", btn_add_client: "+ عميل جديد", search: "البحث بالاسم...",
        th_photo: "صورة", th_name: "الاسم الكامل", th_contact: "الاتصال", th_email: "البريد الإلكتروني", th_account: "الحساب", th_actions: "الإجراءات",
        th_role: "الدور", th_salary: "الراتب", title_emp: "إدارة الموظفين", btn_add_emp: "إضافة موظف",
        welcome: "مرحبًا بك في لوحة تحكم المدير", card_clients: "إجمالي العملاء", card_rooms: "الغرف المتاحة", card_bookings: "إجمالي الحجوزات", card_revenue: "إجمالي الإيرادات", card_stock: "تنبيهات المخزون"
    }
};

function applyTranslations(lang) {
    localStorage.setItem('preferredLang', lang);
    document.querySelectorAll('[data-translate]').forEach(el => {
        const key = el.getAttribute('data-translate');
        if (translations[lang] && translations[lang][key]) {
            el.innerText = translations[lang][key];
        }
    });
    document.body.style.direction = (lang === 'ar') ? 'rtl' : 'ltr';
}