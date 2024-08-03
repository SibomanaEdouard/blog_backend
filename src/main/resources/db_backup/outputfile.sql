--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: comments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comments (
    id uuid NOT NULL,
    content character varying(255) NOT NULL,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    author uuid NOT NULL,
    post uuid NOT NULL
);


ALTER TABLE public.comments OWNER TO postgres;

--
-- Name: posts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.posts (
    id uuid NOT NULL,
    content character varying(255) NOT NULL,
    created_at timestamp(6) without time zone,
    title character varying(255) NOT NULL,
    updated_at timestamp(6) without time zone,
    author uuid NOT NULL
);


ALTER TABLE public.posts OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone,
    email character varying(100) NOT NULL,
    firstname character varying(255) NOT NULL,
    lastname character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    updated_at timestamp(6) without time zone
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Data for Name: comments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.comments (id, content, created_at, updated_at, author, post) FROM stdin;
ebfbbc47-f15b-4cdc-bd46-99f0864d0d65	ABout this is my testin comment	2024-08-03 09:53:03.403	2024-08-03 09:53:03.403	d5375c2d-f16d-44ad-b637-3ff738fc7a0c	c7f78560-dafd-43dd-9613-ce08f15165db
a95dd95c-dc4b-42d2-90f1-c8f12bbe1a34	Twika hashye right now	2024-08-03 09:53:21.371	2024-08-03 09:53:21.371	d5375c2d-f16d-44ad-b637-3ff738fc7a0c	c7f78560-dafd-43dd-9613-ce08f15165db
03102376-4e39-4db9-8225-832bcc6c1e0b	This is my comment on the frontend	2024-08-03 11:25:19.244	2024-08-03 11:25:19.244	db7b4149-2807-4360-8b28-054c57b12351	39a36fd5-d1c6-487e-a6e9-c3325f927e32
15d7540a-fa58-440c-8ee5-858c0c62cd89	I want to be serious developer	2024-08-03 14:44:15.949	2024-08-03 14:44:31.096	db7b4149-2807-4360-8b28-054c57b12351	39a36fd5-d1c6-487e-a6e9-c3325f927e32
bbbca73f-e3b7-4ff0-9219-d52ac7e6e73d	ITs time to work	2024-08-03 15:21:15.176	2024-08-03 15:21:40.114	b11c751b-38b6-4b91-a038-e04db7f233ea	39a36fd5-d1c6-487e-a6e9-c3325f927e32
cf3bac17-cd20-43a0-ae14-705380ea427e	Welcome 	2024-08-03 15:23:12.641	2024-08-03 15:23:12.641	b11c751b-38b6-4b91-a038-e04db7f233ea	d5a5dbfa-6d8c-40c0-9528-3bb02c0b7fdd
f8afcf70-ed06-45ce-b1c6-77e3018468cf	Its time to make every possible thing	2024-08-03 15:25:34.529	2024-08-03 15:25:34.529	b11c751b-38b6-4b91-a038-e04db7f233ea	d5a5dbfa-6d8c-40c0-9528-3bb02c0b7fdd
\.


--
-- Data for Name: posts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.posts (id, content, created_at, title, updated_at, author) FROM stdin;
39a36fd5-d1c6-487e-a6e9-c3325f927e32	Umuganda ni igikorwa cyiza	2024-08-03 10:10:13.808	Umuganda	2024-08-03 10:10:13.808	d5375c2d-f16d-44ad-b637-3ff738fc7a0c
a6d66e1a-cb86-44a3-a381-e03f241911bc	This is  testing me 	2024-08-03 10:11:06.322	Testing	2024-08-03 10:11:06.322	d5375c2d-f16d-44ad-b637-3ff738fc7a0c
7ad7b8b0-b516-411b-8671-2c1f36fac76c	This is  testing me 	2024-08-03 10:11:09.241	Testing	2024-08-03 10:11:09.241	d5375c2d-f16d-44ad-b637-3ff738fc7a0c
d1b05f9f-779a-416b-90dd-3fc4c04ee2d5	This is  testing me 	2024-08-03 10:11:10.504	Testing	2024-08-03 10:11:10.504	d5375c2d-f16d-44ad-b637-3ff738fc7a0c
d5a5dbfa-6d8c-40c0-9528-3bb02c0b7fdd	This is accounting	2024-08-03 10:11:44.342	Accounting	2024-08-03 10:11:44.342	d5375c2d-f16d-44ad-b637-3ff738fc7a0c
c7f78560-dafd-43dd-9613-ce08f15165db	This is accounting and update 	2024-08-02 09:59:15.365	Updating	2024-08-03 14:12:12.141	d5375c2d-f16d-44ad-b637-3ff738fc7a0c
e4ccb0b4-7268-46c5-8197-c310e218f94b	I will be there to help you	2024-08-03 11:54:09.096	Tesing blog	2024-08-03 14:45:18.387	db7b4149-2807-4360-8b28-054c57b12351
5fe2593a-d5a6-48b6-b9d8-0a1affd93d3e	Testing posting	2024-08-03 14:46:52.772	Testing last	2024-08-03 14:46:52.772	db7b4149-2807-4360-8b28-054c57b12351
3e7c19bc-286a-4cf9-8e6f-8ef3649a132b	This is latest test	2024-08-03 15:07:25.783	Last	2024-08-03 15:07:25.783	b11c751b-38b6-4b91-a038-e04db7f233ea
7533d645-a934-4569-b508-06df332ae9b7	TEST	2024-08-03 15:34:32.885	TEST	2024-08-03 15:34:32.885	b11c751b-38b6-4b91-a038-e04db7f233ea
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, created_at, email, firstname, lastname, password, updated_at) FROM stdin;
d5375c2d-f16d-44ad-b637-3ff738fc7a0c	2024-08-02 09:34:23.104	edd@gmail.com	Edd	Sibo	$2a$10$J77MFc9dlByCcRpPwoctNuVQhnFckInF4wOyrxvzfkgyLnecLeXYq	2024-08-02 09:34:23.105
9554fe0d-cc70-4ba3-b3e6-449838088771	2024-08-02 15:04:47.584	edd1@gmail.com	Edd	Sibo	$2a$10$dsYfph0WrOYoTgpTgQF1be.e7OTmp1v3AillV3qBVUzsXRL0Yxet.	2024-08-02 15:04:47.584
6dbb5b12-d012-4b2c-9665-30417d5a8d76	2024-08-02 15:36:14.806	sibomanaedouard974@gmail.com	edouard	sibo	$2a$10$o4cuUK7/PBVvDJUBa5eV0uBfGABo.gDdf36PTDNWeOxl4rjtvO7E.	2024-08-02 15:36:14.806
db7b4149-2807-4360-8b28-054c57b12351	2024-08-02 15:52:55.919	sibomanaedouard19@gmail.com	SIBOMANA	Edouard	$2a$10$3EVY7nyNlTcpFz.S3ZbcwuMvL.aejn4jXPUIz/HW1zhLY06zS2Ev6	2024-08-02 15:52:55.921
df1607e1-c57c-4e4a-a5ac-1d1d140e2db8	2024-08-02 17:45:25.974	siboedd@gmail.com	Edd	Edd sibo	$2a$10$qxUfn46L7oHrJW9zF4sfje4p.xEATGWlEallPozqGtov9pdGFVMsO	2024-08-02 17:45:25.974
c0852ec8-ad77-4974-aec2-18edb8c86317	2024-08-03 07:36:14.459	sibomanaedouard74@gmail.com	SIBOMANA	Edouard	$2a$10$/Ap8K6roSpvmfMizr5E1Q.B98Dg/nwVbmhUyJlqZuWbB02QBA/.ka	2024-08-03 07:36:14.459
b11c751b-38b6-4b91-a038-e04db7f233ea	2024-08-03 15:00:22.883	praise@gmail.com	chantal	praise	$2a$10$H2zxizgYAGhX0R58X7ciVOn7JjS/pSuidDDYF5OuTNiUTy9p1gqEm	2024-08-03 15:00:22.883
\.


--
-- Name: comments comments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);


--
-- Name: posts posts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.posts
    ADD CONSTRAINT posts_pkey PRIMARY KEY (id);


--
-- Name: users uk6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: posts fk9k4xbcojf8g0etikyd7j5p1b3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.posts
    ADD CONSTRAINT fk9k4xbcojf8g0etikyd7j5p1b3 FOREIGN KEY (author) REFERENCES public.users(id);


--
-- Name: comments fkmcsi74v5msx3nsrk6mnwe455d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT fkmcsi74v5msx3nsrk6mnwe455d FOREIGN KEY (post) REFERENCES public.posts(id);


--
-- Name: comments fkp6ilf8rosuwl497khjofovggk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT fkp6ilf8rosuwl497khjofovggk FOREIGN KEY (author) REFERENCES public.users(id);


--
-- PostgreSQL database dump complete
--

