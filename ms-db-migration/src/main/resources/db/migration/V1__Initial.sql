CREATE TABLE public.countries (
    id integer NOT NULL,
    name character varying NOT NULL,
    iso_code character(3) NOT NULL,
    phone_code character(8)
);


ALTER TABLE public.countries OWNER TO freeland;

--
-- Name: countries_id_seq; Type: SEQUENCE; Schema: public; Owner: freeland
--

CREATE SEQUENCE public.countries_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.countries_id_seq OWNER TO freeland;

--
-- Name: countries_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: freeland
--

ALTER SEQUENCE public.countries_id_seq OWNED BY public.countries.id;

--
-- Name: roles; Type: TABLE; Schema: public; Owner: freeland
--

CREATE TABLE public.roles (
    id integer NOT NULL,
    name character varying(45) NOT NULL,
    description character varying(256)
);


ALTER TABLE public.roles OWNER TO freeland;

--
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: freeland
--

CREATE SEQUENCE public.roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roles_id_seq OWNER TO freeland;

--
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: freeland
--

ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;

--
-- Name: users; Type: TABLE; Schema: public; Owner: freeland
--

CREATE TABLE public.users (
    id integer NOT NULL,
    username character varying(45) NOT NULL,
    email character varying(255) NOT NULL UNIQUE,
    password character varying(255) NOT NULL,
    status smallint DEFAULT 1 NOT NULL,
    created_date timestamp NOT NULL DEFAULT NOW(),
    edited_date timestamp
);


ALTER TABLE public.users OWNER TO freeland;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: freeland
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO freeland;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: freeland
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;

--
-- Name: users_profiles; Type: TABLE; Schema: public; Owner: freeland
--

CREATE TABLE public.users_profiles (
    user_id bigint NOT NULL,
    first_name character varying,
    last_name character varying,
    birthday date,
    nationality integer,
    city character varying,
    address character varying,
    phone character varying
);


ALTER TABLE public.users_profiles OWNER TO freeland;

--
-- Name: users_roles; Type: TABLE; Schema: public; Owner: freeland
--

CREATE TABLE public.users_roles (
    user_id integer NOT NULL,
    role_id integer NOT NULL
);


ALTER TABLE public.users_roles OWNER TO freeland;

--
-- Name: users_password_reset; Type: TABLE; Schema: public; Owner: freeland
--

CREATE TABLE public.users_password_reset (
    user_id integer NOT NULL,
    reset_token character varying(255) NOT NULL,
    expire_date timestamp NOT NULL
);


ALTER TABLE public.users_password_reset OWNER TO freeland;

--
-- Name: users_account_activation; Type: TABLE; Schema: public; Owner: freeland
--

CREATE TABLE public.users_account_activation (
    user_id integer NOT NULL,
    activation_token character varying(255) NOT NULL,
    expire_date timestamp NOT NULL
);


ALTER TABLE public.users_account_activation OWNER TO freeland;

--
-- Name: countries id; Type: DEFAULT; Schema: public; Owner: freeland
--

ALTER TABLE ONLY public.countries ALTER COLUMN id SET DEFAULT nextval('public.countries_id_seq'::regclass);

--
-- Name: roles id; Type: DEFAULT; Schema: public; Owner: freeland
--

ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);

--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: freeland
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);

--
-- Data for Name: countries; Type: TABLE DATA; Schema: public; Owner: freeland
--

COPY public.countries (id, name, iso_code, phone_code) FROM stdin;
1	Afghanistan	AF	93
2	Albania	AL	355
3	Algeria	DZ	213
4	American Samoa	AS	1-684
5	Andorra	AD	376
6	Angola	AO	244
7	Anguilla	AI	1-264
8	Antarctica	AQ	672
9	Antigua and Barbuda	AG	1-268
10	Argentina	AR	54
11	Armenia	AM	374
12	Aruba	AW	297
13	Australia	AU	61
14	Austria	AT	43
15	Azerbaijan	AZ	994
16	Bahamas	BS	1-242
17	Bahrain	BH	973
18	Bangladesh	BD	880
19	Barbados	BB	1-246
20	Belarus	BY	375
21	Belgium	BE	32
22	Belize	BZ	501
23	Benin	BJ	229
24	Bermuda	BM	1-441
25	Bhutan	BT	975
26	Bolivia	BO	591
27	Bosnia and Herzegowina	BA	387
28	Botswana	BW	267
29	Bouvet Island	BV	47
30	Brazil	BR	55
31	British Indian Ocean Territory	IO	246
32	Brunei Darussalam	BN	673
33	Bulgaria	BG	359
34	Burkina Faso	BF	226
35	Burundi	BI	257
36	Cambodia	KH	855
37	Cameroon	CM	237
38	Canada	CA	1
39	Cape Verde	CV	238
40	Cayman Islands	KY	1-345
41	Central African Republic	CF	236
42	Chad	TD	235
43	Chile	CL	56
44	China	CN	86
45	Christmas Island	CX	61
46	Cocos (Keeling) Islands	CC	61
47	Colombia	CO	57
48	Comoros	KM	269
49	Congo Democratic Republic of	CG	242
50	Cook Islands	CK	682
51	Costa Rica	CR	506
52	Cote D'Ivoire	CI	225
53	Croatia	HR	385
54	Cuba	CU	53
55	Cyprus	CY	357
56	Czech Republic	CZ	420
57	Denmark	DK	45
58	Djibouti	DJ	253
59	Dominica	DM	1-767
60	Dominican Republic	DO	1-809
61	Timor-Leste	TL	670
62	Ecuador	EC	593
63	Egypt	EG	20
64	El Salvador	SV	503
65	Equatorial Guinea	GQ	240
66	Eritrea	ER	291
67	Estonia	EE	372
68	Ethiopia	ET	251
69	Falkland Islands (Malvinas)	FK	500
70	Faroe Islands	FO	298
71	Fiji	FJ	679
72	Finland	FI	358
73	France	FR	33
75	French Guiana	GF	594
76	French Polynesia	PF	689
77	French Southern Territories	TF	262
78	Gabon	GA	241
79	Gambia	GM	220
80	Georgia	GE	995
81	Germany	DE	49
82	Ghana	GH	233
83	Gibraltar	GI	350
84	Greece	GR	30
85	Greenland	GL	299
86	Grenada	GD	1-473
87	Guadeloupe	GP	590
88	Guam	GU	1-671
89	Guatemala	GT	502
90	Guinea	GN	224
91	Guinea-bissau	GW	245
92	Guyana	GY	592
93	Haiti	HT	509
94	Heard Island and McDonald Islands	HM	011
95	Honduras	HN	504
96	Hong Kong	HK	852
97	Hungary	HU	36
98	Iceland	IS	354
99	India	IN	91
100	Indonesia	ID	62
101	Iran (Islamic Republic of)	IR	98
102	Iraq	IQ	964
103	Ireland	IE	353
104	Israel	IL	972
105	Italy	IT	39
106	Jamaica	JM	1-876
107	Japan	JP	81
108	Jordan	JO	962
109	Kazakhstan	KZ	7 
110	Kenya	KE	254
111	Kiribati	KI	686
112	Korea, Democratic People's Republic of	KP	850
113	South Korea	KR	82
114	Kuwait	KW	965
115	Kyrgyzstan	KG	996
116	Lao People's Democratic Republic	LA	856
117	Latvia	LV	371
118	Lebanon	LB	961
119	Lesotho	LS	266
120	Liberia	LR	231
121	Libya	LY	218
122	Liechtenstein	LI	423
123	Lithuania	LT	370
124	Luxembourg	LU	352
125	Macao	MO	853
126	Macedonia, The Former Yugoslav Republic of	MK	389
127	Madagascar	MG	261
128	Malawi	MW	265
129	Malaysia	MY	60
130	Maldives	MV	960
131	Mali	ML	223
132	Malta	MT	356
133	Marshall Islands	MH	692
134	Martinique	MQ	596
135	Mauritania	MR	222
136	Mauritius	MU	230
137	Mayotte	YT	262
138	Mexico	MX	52
139	Micronesia, Federated States of	FM	691
140	Moldova	MD	373
141	Monaco	MC	377
142	Mongolia	MN	976
143	Montserrat	MS	1-664
144	Morocco	MA	212
145	Mozambique	MZ	258
146	Myanmar	MM	95
147	Namibia	NA	264
148	Nauru	NR	674
149	Nepal	NP	977
150	Netherlands	NL	31
151	Netherlands Antilles	AN	599
152	New Caledonia	NC	687\t
153	New Zealand	NZ	64
154	Nicaragua	NI	505
155	Niger	NE	227
156	Nigeria	NG	234
157	Niue	NU	683
158	Norfolk Island	NF	672
159	Northern Mariana Islands	MP	1-670
160	Norway	NO	47
161	Oman	OM	968
162	Pakistan	PK	92
163	Palau	PW	680
164	Panama	PA	507
165	Papua New Guinea	PG	675
166	Paraguay	PY	595
167	Peru	PE	51
168	Philippines	PH	63
169	Pitcairn	PN	64
170	Poland	PL	48
171	Portugal	PT	351
172	Puerto Rico	PR	1-787
173	Qatar	QA	974
174	Reunion	RE	262
175	Romania	RO	40
176	Russian Federation	RU	7 
177	Rwanda	RW	250
178	Saint Kitts and Nevis	KN	1-869
179	Saint Lucia	LC	1-758
180	Saint Vincent and the Grenadines	VC	1-784
181	Samoa	WS	685
182	San Marino	SM	378
183	Sao Tome and Principe	ST	239
184	Saudi Arabia	SA	966
185	Senegal	SN	221
186	Seychelles	SC	248
187	Sierra Leone	SL	232
188	Singapore	SG	65
189	Slovakia (Slovak Republic)	SK	421
190	Slovenia	SI	386
191	Solomon Islands	SB	677
192	Somalia	SO	252
193	South Africa	ZA	27
194	South Georgia and the South Sandwich Islands	GS	500
195	Spain	ES	34
196	Sri Lanka	LK	94
197	Saint Helena, Ascension and Tristan da Cunha	SH	290
198	St. Pierre and Miquelon	PM	508
199	Sudan	SD	249
200	Suriname	SR	597
201	Svalbard and Jan Mayen Islands	SJ	47
202	Swaziland	SZ	268
203	Sweden	SE	46
204	Switzerland	CH	41
205	Syrian Arab Republic	SY	963
206	Taiwan	TW	886
207	Tajikistan	TJ	992
208	Tanzania, United Republic of	TZ	255
209	Thailand	TH	66
210	Togo	TG	228
211	Tokelau	TK	690
212	Tonga	TO	676
213	Trinidad and Tobago	TT	1-868
214	Tunisia	TN	216
215	Turkey	TR	90
216	Turkmenistan	TM	993
217	Turks and Caicos Islands	TC	1-649
218	Tuvalu	TV	688
219	Uganda	UG	256
220	Ukraine	UA	380
221	United Arab Emirates	AE	971
222	United Kingdom	GB	44
223	United States	US	1 
224	United States Minor Outlying Islands	UM	246
225	Uruguay	UY	598
226	Uzbekistan	UZ	998
227	Vanuatu	VU	678
228	Vatican City State (Holy See)	VA	379
229	Venezuela	VE	58
230	Vietnam	VN	84
231	Virgin Islands (British)	VG	1-284
232	Virgin Islands (U.S.)	VI	1-340
233	Wallis and Futuna Islands	WF	681
234	Western Sahara	EH	212
235	Yemen	YE	967
236	Serbia	RS	381
238	Zambia	ZM	260
239	Zimbabwe	ZW	263
240	Aaland Islands	AX	358
241	Palestine	PS	970
242	Montenegro	ME	382
243	Guernsey	GG	44-1481 
244	Isle of Man	IM	44-1624 
245	Jersey	JE	44-1534 
247	Curaçao	CW	599
248	Ivory Coast	CI	225
249	Kosovo	XK	383
\.

--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: freeland
--

COPY public.roles (id, name, description) FROM stdin;
1	ROLE_ADMIN	Administratoriaus teisė
2	ROLE_USER	Vartotojo teisė
\.

--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: freeland
--

COPY public.users (id, username, email, password, status) FROM stdin;
1	freeland	freeland@gmail.com	$2a$04$vL2bsMXpnEz3amJyjtkKM.r9Sg6t326iROkwUtdIu1WwzY1.jiTWG	1
2	zolfun	freelando@gmail.com	$2a$04$vL2bsMXpnEz3amJyjtkKM.r9Sg6t326iROkwUtdIu1WwzY1.jiTWG	1
3	gongon89	gogo@gmail.com	$2a$04$vL2bsMXpnEz3amJyjtkKM.r9Sg6t326iROkwUtdIu1WwzY1.jiTWG	1
4	kafka	kafka.mafka@gmail.com	$2a$04$vL2bsMXpnEz3amJyjtkKM.r9Sg6t326iROkwUtdIu1WwzY1.jiTWG	1
5	hobit96	hobotas@gmail.com	$2a$04$vL2bsMXpnEz3amJyjtkKM.r9Sg6t326iROkwUtdIu1WwzY1.jiTWG	1
6	bananarama	j.rama52@lilo.lt	$2a$04$vL2bsMXpnEz3amJyjtkKM.r9Sg6t326iROkwUtdIu1WwzY1.jiTWG	1
7	tucan56	dauma96@saga.com	$2a$04$vL2bsMXpnEz3amJyjtkKM.r9Sg6t326iROkwUtdIu1WwzY1.jiTWG	1
8	barbeque	bb.kolins@gmail.com	$2a$04$vL2bsMXpnEz3amJyjtkKM.r9Sg6t326iROkwUtdIu1WwzY1.jiTWG	1
9	homeland89	j.lomo@masachusets.org	$2a$04$vL2bsMXpnEz3amJyjtkKM.r9Sg6t326iROkwUtdIu1WwzY1.jiTWG	1
10	89Trusa	eugfene.kaspersky@gmail.com	$2a$04$vL2bsMXpnEz3amJyjtkKM.r9Sg6t326iROkwUtdIu1WwzY1.jiTWG	1
11	trololo	Satoshi@gmail.com	$2a$04$vL2bsMXpnEz3amJyjtkKM.r9Sg6t326iROkwUtdIu1WwzY1.jiTWG	1
12	spartan	Sasha.coe@gmail.com	$2a$04$vL2bsMXpnEz3amJyjtkKM.r9Sg6t326iROkwUtdIu1WwzY1.jiTWG	1
13	dudikas	j.zabiela@gmail.com	$2a$04$vL2bsMXpnEz3amJyjtkKM.r9Sg6t326iROkwUtdIu1WwzY1.jiTWG	1
14	svencis	anna.maria.x@324234wdw.lt	$2a$04$vL2bsMXpnEz3amJyjtkKM.r9Sg6t326iROkwUtdIu1WwzY1.jiTWG	1
15	gonoreja	alien.e@erer.com	$2a$04$vL2bsMXpnEz3amJyjtkKM.r9Sg6t326iROkwUtdIu1WwzY1.jiTWG	1
16	hablas	misskittin@erer.com	$2a$04$vL2bsMXpnEz3amJyjtkKM.r9Sg6t326iROkwUtdIu1WwzY1.jiTWG	1
17	fenix	j.digweed@bedrock.com	$2a$04$vL2bsMXpnEz3amJyjtkKM.r9Sg6t326iROkwUtdIu1WwzY1.jiTWG	1
18	dundulis89	emerson69@eerere.com	$2a$04$vL2bsMXpnEz3amJyjtkKM.r9Sg6t326iROkwUtdIu1WwzY1.jiTWG	1
\.

--
-- Data for Name: users_profiles; Type: TABLE DATA; Schema: public; Owner: freeland
--

COPY public.users_profiles (user_id, first_name, last_name, birthday, nationality, city, address, phone) FROM stdin;
1	Rokas	Sabaliauskas	1983-08-12	123	Kaunas	Dubingių 10-75	+37060027493
2	Jonas	Milijonas	1944-08-12	123	Kaunas	Dubingių 10-75	+37060027493
3	Lina	Cepelina	1986-04-26	55	Kaunas	Dubingių 10-75	+37060027493
4	Andrius	Mandrius	1977-08-12	36	Kaunas	Dubingių 10-75	+37060027493
5	Ismael	Al Apu	1955-01-14	100	Kaunas	Dubingių 10-75	+37060027493
6	Juana	Ramanauskienė	1996-11-01	85	Kaunas	Dubingių 10-75	+37060027493
7	Daumantas	Kairiavičius	1983-02-07	45	Kaunas	Dubingių 10-75	+37060027493
8	John	Mellon	2000-11-18	56	Kaunas	Dubingių 10-75	+37060027493
9	Lynda	Hamilton	1963-08-01	123	Kaunas	Dubingių 10-75	+37060027493
10	Eugene	kaspersky	2001-05-13	78	Kaunas	Dubingių 10-75	+37060027493
11	Satoshi	Tommie	1944-08-12	123	Tokyo	Dubingių 10-75	+37060027493
12	Sasha	Coe	1986-04-26	55	San Francisco	Dubingių 10-75	+37060027493
13	James	Zabiela	1977-08-12	36	Chicago	Dubingių 10-75	+37060027493
14	Anna	Maria	1955-01-14	100	Athens	Dubingių 10-75	+37060027493
15	Ellen	Allien	1996-11-01	85	Berlin	Dubingių 10-75	+37060027493
16	Miss	Kitten	1983-02-07	45	Berlin	Dubingių 10-75	+37060027493
17	James	Lavelle	2000-11-18	56	Bristol	Dubingių 10-75	+37060027493
18	John	Digweed	1963-08-01	123	London	Dubingių 10-75	+37060027493
\.

--
-- Data for Name: users_roles; Type: TABLE DATA; Schema: public; Owner: freeland
--

COPY public.users_roles (user_id, role_id) FROM stdin;
1	1
2	2
3	2
4	2
5	2
6	2
7	2
8	2
9	2
10	2
11	2
12	2
13	2
14	2
15	2
16	2
17	2
18	2
\.

--
-- Name: countries_id_seq; Type: SEQUENCE SET; Schema: public; Owner: freeland
--

SELECT pg_catalog.setval('public.countries_id_seq', 1, false);

--
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: freeland
--

SELECT pg_catalog.setval('public.roles_id_seq', 2, true);

--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: freeland
--

SELECT pg_catalog.setval('public.users_id_seq', 39, true);

--
-- Name: countries countries_pkey; Type: CONSTRAINT; Schema: public; Owner: freeland
--

ALTER TABLE ONLY public.countries ADD CONSTRAINT countries_pkey PRIMARY KEY (id);

--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: freeland
--

ALTER TABLE ONLY public.roles ADD CONSTRAINT roles_pkey PRIMARY KEY (id);

--
-- Name: users_roles uni_userid_role; Type: CONSTRAINT; Schema: public; Owner: freeland
--

ALTER TABLE ONLY public.users_roles ADD CONSTRAINT uni_userid_role UNIQUE (user_id, role_id);

--
-- Name: users_profiles users_data_pkey; Type: CONSTRAINT; Schema: public; Owner: freeland
--

ALTER TABLE ONLY public.users_profiles ADD CONSTRAINT users_data_pkey PRIMARY KEY (user_id);

--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: freeland
--

ALTER TABLE ONLY public.users ADD CONSTRAINT users_pkey PRIMARY KEY (id);

--
-- Name: users_roles fk_role_id; Type: FK CONSTRAINT; Schema: public; Owner: freeland
--

ALTER TABLE ONLY public.users_roles ADD CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES public.roles(id);

--
-- Name: users_roles fk_user_id; Type: FK CONSTRAINT; Schema: public; Owner: freeland
--

ALTER TABLE ONLY public.users_roles ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.users(id);